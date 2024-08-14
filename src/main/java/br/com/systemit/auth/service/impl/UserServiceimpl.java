package br.com.systemit.auth.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.domain.enums.ResultProcessing;
import br.com.systemit.auth.domain.repository.UserProfileRepository;
import br.com.systemit.auth.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceimpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserWithPermission(String login){
        
        Optional<User> userOptional = userRepository.findByLogin(login);
        
        if(userOptional.isEmpty()){
            return null;
        }

        User user = userOptional.get();
        List<String> profiles = userProfileRepository.findPermissionsByUser(user);
        user.setRoles(profiles);

        return user;
    }

    public UserDetails autenticar( User usuario ){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches( usuario.getPassword(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new br.com.systemit.auth.exception.AuthException(ResultProcessing.INVALID_PASSWORD);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //String[] roles = new String[]{"admin", "user"};
        List<String> profiles = userProfileRepository.findPermissionsByUser(user);
        String[] roles = profiles.toArray(new String[0]);

        return org.springframework.security.core.userdetails.User.builder().username(user.getLogin()).password(user.getPassword()).roles(roles).build();

    }

    
}

package br.com.systemit.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.domain.enums.ResultProcessing;
import br.com.systemit.auth.domain.repository.UserRepository;
import br.com.systemit.auth.exception.AuthException;
import jakarta.transaction.Transactional;

@Service
public class UserServiceimpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public UserDetails autenticar( User user ){
        UserDetails userDetail = loadUserByUsername(user.getLogin());
        boolean senhasBatem = passwordEncoder.matches( user.getPassword(), userDetail.getPassword() );

        if(senhasBatem){
            return userDetail;
        }

        throw new AuthException(ResultProcessing.INVALID_PASSWORD);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // waiting entity persmission to be created
        String[] roles = new String[]{"admin", "user"};

        return org.springframework.security.core.userdetails.User.builder().username(user.getLogin()).password(user.getPassword()).roles(roles).build();

    }

    
}

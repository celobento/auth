package br.com.systemit.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.domain.entity.UserProfile;
import br.com.systemit.auth.domain.repository.UserProfileRepository;
import br.com.systemit.auth.domain.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserServiceimpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // waiting entity persmission to be created
        //String[] roles = new String[]{"admin", "user"};
        List<String> profiles = userProfileRepository.findPermissionsByUser(user);
        String[] roles = profiles.toArray(new String[0]);

        return org.springframework.security.core.userdetails.User.builder().username(user.getLogin()).password(user.getPassword()).roles(roles).build();

    }

    
}

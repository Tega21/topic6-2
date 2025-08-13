package com.gcu.topic61.business;

import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessService implements UserDetailsService {

    private final UsersDataService service;

    @Autowired
    public UserBusinessService(UsersDataService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== UserBusinessService.loadUserByUsername called ===");
        System.out.println("Looking for username: " + username);

        // Find user in database
        UserEntity user = service.findByUsername(username);

        if (user == null) {
            System.out.println("User not found in database: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        System.out.println("User found in database:");
        System.out.println("- Username: " + user.getUsername());
        System.out.println("- Password (encrypted): " + user.getPassword());

        // Test password verification manually
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String testPassword = "test";
        boolean matches = encoder.matches(testPassword, user.getPassword());
        System.out.println("- Password verification test: " + matches);
        System.out.println("- Testing password 'test' against stored hash");

        // Return Spring Security User object
        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Password should already be encoded in database
                .roles("USER")
                .build();

        System.out.println("UserDetails created successfully");
        System.out.println("- UserDetails username: " + userDetails.getUsername());
        System.out.println("- UserDetails password: " + userDetails.getPassword());
        System.out.println("- UserDetails authorities: " + userDetails.getAuthorities());

        return userDetails;
    }
}
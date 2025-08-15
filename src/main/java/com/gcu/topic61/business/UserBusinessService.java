package com.gcu.topic61.business;

import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessService implements UserDetailsService {

    private final UsersDataService users;

    public UserBusinessService(UsersDataService users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u = users.findByUsername(username);
        if (u == null) throw new UsernameNotFoundException("User not found: " + username);

        // Return the stored BCrypt hash as-is (do not re-encode here)
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
}

package com.gcu.topic61.business;

import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessService implements UserDetailsService {

    private final UsersDataService users;

    public UserBusinessService(UsersDataService users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== LOGIN ATTEMPT DEBUG ===");
        System.out.println("Looking for user: " + username);

        UserEntity u = users.findByUsername(username);

        if (u == null) {
            System.out.println("USER NOT FOUND IN DATABASE!");
            throw new UsernameNotFoundException("User not found: " + username);
        }

        System.out.println("User found! Username: " + u.getUsername());
        System.out.println("Password from DB: " + u.getPassword());
        System.out.println("Password length: " + u.getPassword().length());
        System.out.println("Is BCrypt hash? " + u.getPassword().startsWith("$2a$"));

        // Test if "test" matches the stored password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches("test", u.getPassword());
        System.out.println("Does 'test' match stored password? " + matches);

        if (!u.getPassword().startsWith("$2a$")) {
            System.out.println("WARNING: Password is NOT BCrypt encoded!");
            System.out.println("Generating correct BCrypt hash for 'test':");
            String correctHash = encoder.encode("test");
            System.out.println("USE THIS IN MONGODB: " + correctHash);
        }

        System.out.println("=== END DEBUG ===");

        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
}
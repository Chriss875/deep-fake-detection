package com.IS336.PROJECT.config;

import com.IS336.PROJECT.model.User;
import com.IS336.PROJECT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.IS336.PROJECT.model.UserRoles;

@Component
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createTestUser("ian@gmail.com", "12345678", UserRoles.USER);
        createTestUser("mbije@gmail.com", "password2", UserRoles.USER);
        createTestUser("munda@gmail.com", "password3", UserRoles.USER);
        createTestUser("mburu@gmail.com", "password4", UserRoles.USER);
        createTestUser("katende@gmail.com", "password5", UserRoles.ADMIN);
    }

    private void createTestUser(String email, String rawPassword, UserRoles role) {
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            userRepository.save(user);
            System.out.println("Created test user: " + email);
        }
    }
}

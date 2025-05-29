package com.IS336.PROJECT.config;

import com.IS336.PROJECT.model.User;
import com.IS336.PROJECT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createTestUser("user1", "12345678");
        createTestUser("user2", "password2");
        createTestUser("user3", "password3");
        createTestUser("user4", "password4");
        createTestUser("user5", "password5");
    }

    private void createTestUser(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(rawPassword));
            userRepository.save(user);
            System.out.println("Created test user: " + username);
        }
    }
}

package com.IS336.PROJECT.service;

import org.springframework.stereotype.Service;
import com.IS336.PROJECT.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import com.IS336.PROJECT.config.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import com.IS336.PROJECT.dto.LoginRequest;
import com.IS336.PROJECT.model.User;
import java.util.Optional;
import com.IS336.PROJECT.exception.InvalidCredentialsException;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtil;

    public String login(LoginRequest request) {
    String password = request.getPassword();
    Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
    if (userOptional.isEmpty()) {
        throw new EntityNotFoundException("User not found");
    }
    User user = userOptional.get();
    boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
    if (passwordMatches) {
        String token = jwtUtil.generateToken(user.getUsername());
        return token;
    } else {
        throw new InvalidCredentialsException("UNAUTHORIZED", "Invalid credentials");
    }
    
}
}


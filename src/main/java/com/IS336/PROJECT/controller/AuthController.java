package com.IS336.PROJECT.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.IS336.PROJECT.dto.LoginRequest;
import com.IS336.PROJECT.service.LoginService;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String responseBody = loginService.login(request);
        Map<String, String> response = new HashMap<>();
        response.put("token", responseBody);
        return ResponseEntity.ok().body(response);
    }
}

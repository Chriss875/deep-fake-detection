package com.IS336.PROJECT.controller;

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
        Map<String, String> responseBody = loginService.login(request);
        return ResponseEntity.ok().body(responseBody);
    }
}

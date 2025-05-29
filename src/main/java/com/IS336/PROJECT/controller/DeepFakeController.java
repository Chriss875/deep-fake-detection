package com.IS336.PROJECT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import com.IS336.PROJECT.service.DeepFakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeepFakeController {
    private final DeepFakeService deepfakeDetectionService;

@PostMapping("/detect")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Object result= deepfakeDetectionService.detectDeepFake(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/result")
    public String resultPage() {
        return "result";
    }
}
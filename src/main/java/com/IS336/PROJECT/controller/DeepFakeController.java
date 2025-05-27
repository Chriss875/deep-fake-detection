package com.IS336.PROJECT.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import com.IS336.PROJECT.service.DeepFakeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeepFakeController {
    private final DeepFakeService deepFakeService;

    @PostMapping("/detect-deepfake")
    public ResponseEntity<String> detectDeepfake(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "req_id") String reqId,
            @RequestParam(value = "isIOS", defaultValue = "false") boolean isIOS,
            @RequestParam(value = "orientation", defaultValue = "0") int orientation) {
        try {
            if (!file.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("Only image files are supported");
            }
            String result = deepFakeService.detectDeepfake(file, reqId, "image", isIOS, orientation);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error detecting deepfake: " + e.getMessage());
        }
    }
}
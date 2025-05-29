package com.IS336.PROJECT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RestController;
import com.IS336.PROJECT.service.DeepFakeService;
import lombok.RequiredArgsConstructor;
import com.IS336.PROJECT.dto.DeepFakeResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeepFakeController {
    private final DeepFakeService deepfakeDetectionService;

@PostMapping("/detect")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            DeepFakeResponse response = deepfakeDetectionService.detectDeepfake(file);
            redirectAttributes.addFlashAttribute("detectionResult", response);
            return "redirect:/result";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Deepfake detection failed: " + e.getMessage());
            return "redirect:/upload";
        }
    }

    @GetMapping("/result")
    public String resultPage() {
        return "result";
    }
}
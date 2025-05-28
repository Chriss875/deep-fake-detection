package com.IS336.PROJECT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.IS336.PROJECT.service.DeepFakeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final DeepFakeService deepFakeService;
    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @GetMapping("/login")
public String showLoginForm() {
    return "login";
}

    @PostMapping("/detect")
    public String detectDeepfake(@RequestParam("file") MultipartFile file, Model model) {
        try {
            if (!file.getContentType().startsWith("image/")) {
                model.addAttribute("error", "Only image files are supported");
                return "upload";
            }
            String reqId = "12345";
            boolean isIOS = false;
            int orientation = 0;
            String result = deepFakeService.detectDeepfake(file, reqId, "image", isIOS, orientation);
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("error", "Error detecting deepfake: " + e.getMessage());
        }
        return "upload";
    }
}
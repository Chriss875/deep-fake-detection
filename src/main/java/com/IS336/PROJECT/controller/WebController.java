package com.IS336.PROJECT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {


    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

}

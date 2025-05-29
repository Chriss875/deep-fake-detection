package com.IS336.PROJECT.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.io.IOException;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.IS336.PROJECT.dto.DeepFakeResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeepFakeService {
    private static final String ARYA_API_URL = "https://ping.arya.ai/api/v1/deepfake-detection/image";
    private static final String ARYA_API_TOKEN = "9d24accbf3606994f57eb2bf4b85fc49";
    private final RestTemplate restTemplate;

    public DeepFakeResponse detectDeepfake(MultipartFile file) throws IOException {
        // Convert image to Base64
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

        // Prepare request payload
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("req_id", UUID.randomUUID().toString());
        requestBody.put("doc_base64", base64Image);
        requestBody.put("doc_type", "image");
        requestBody.put("isIOS", false); // Adjust based on client
        requestBody.put("orientation", 0); // Adjust as needed

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", ARYA_API_TOKEN);

        // Create request entity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Call Arya.ai API
        ResponseEntity<DeepFakeResponse> response = restTemplate.postForEntity(
            ARYA_API_URL, requestEntity, DeepFakeResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("Deepfake detection failed: " + response.getStatusCode());
        }
    }
}
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
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DeepFakeService {
    private static final String ARYA_API_URL = "https://ping.arya.ai/api/v1/deepfake-detection/image";
    private static final String ARYA_API_TOKEN = "9d24accbf3606994f57eb2bf4b85fc49";


    public Map<String, Object> detectDeepFake(MultipartFile file) throws IOException {
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
        Map<String, Object> payload = new HashMap<>();
        payload.put("req_id", UUID.randomUUID().toString());
        payload.put("doc_base64", base64Image);
        payload.put("doc_type", "image");
        payload.put("isIOS", false);
        payload.put("orientation", 0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", ARYA_API_TOKEN);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(ARYA_API_URL, requestEntity, Map.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("API request failed with status: " + responseEntity.getStatusCode());
        }
    }
}
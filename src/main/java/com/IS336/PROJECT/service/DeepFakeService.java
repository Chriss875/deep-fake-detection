package com.IS336.PROJECT.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.IS336.PROJECT.repository.AuditLogRepository;
import com.IS336.PROJECT.model.AuditLog;
import java.util.Base64;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;


@Service
@RequiredArgsConstructor
public class DeepFakeService {
    private final AuditLogRepository auditLogRepository;

    public Map<String, Object> detectDeepFake(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        String docType;
        String apiUrl;

        if (contentType.startsWith("image/")) {
            docType = "image";
            String token = "ca7cabcfa63739c0a22eb2e51cd2a94e";
            apiUrl ="https://ping.arya.ai/api/v1/deepfake-detection/image" ;
            String base64File = Base64.getEncoder().encodeToString(file.getBytes());
            Map<String, Object> payload = new HashMap<>();
            payload.put("req_id", UUID.randomUUID().toString());
            payload.put("doc_base64", base64File);
            payload.put("doc_type", docType);
            payload.put("isIOS", false);
            payload.put("orientation", 0);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("token", token);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName();
                AuditLog auditLog = new AuditLog();
                auditLog.setUsername(email);
                auditLog.setAction("deepfake_detection");
                auditLog.setTimestamp(LocalDateTime.now());
                auditLog.setResult("true");
                auditLogRepository.save(auditLog);
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("API request failed with status: " + responseEntity.getStatusCode());
            }
            


        } else if (contentType.startsWith("video/")) {
            docType = "video";
            apiUrl = "https://ping.arya.ai/api/v1/deepfake-detection/video";
            String token = "ca26fa99f4626a90f325b0e51582af19";
            String base64File = Base64.getEncoder().encodeToString(file.getBytes());
            Map<String, Object> payload = new HashMap<>();
            payload.put("req_id", UUID.randomUUID().toString());
            payload.put("doc_base64", base64File);
            payload.put("doc_type", docType);
            payload.put("isIOS", false);
            payload.put("orientation", 0);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("token", token);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName();
                AuditLog auditLog = new AuditLog();
                auditLog.setUsername(email);
                auditLog.setAction("deepfake_detection");
                auditLog.setTimestamp(LocalDateTime.now());
                auditLog.setResult("true");
                auditLogRepository.save(auditLog);
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("API request failed with status: " + responseEntity.getStatusCode());
            }


        } else if (contentType.startsWith("audio/")) {
            docType = "audio";
            apiUrl = "https://ping.arya.ai/api/v1/deepfake-detection/audio";
            String token = "9e74f7cda53668c6f329e6e349d6f817";
            String base64File = Base64.getEncoder().encodeToString(file.getBytes());
            Map<String, Object> payload = new HashMap<>();
            payload.put("req_id", UUID.randomUUID().toString());
            payload.put("doc_base64", base64File);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("token", token);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName();
                AuditLog auditLog = new AuditLog();
                auditLog.setUsername(email);
                auditLog.setAction("deepfake_detection");
                auditLog.setTimestamp(LocalDateTime.now());
                auditLogRepository.save(auditLog);
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("API request failed with status: " + responseEntity.getStatusCode());
            }

        } else {
            throw new IllegalArgumentException("Unsupported file type: " + contentType);
        }
    }
}
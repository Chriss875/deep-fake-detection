package com.IS336.PROJECT.service;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.io.IOException;

@Service
public class DeepFakeService {

    @Value("${arya.api.token}")
    private String apiToken;

    @Value("${arya.api.url}")
    private String apiUrl;

    public String detectDeepfake(MultipartFile file, String reqId, String docType, boolean isIOS, int orientation) throws IOException {
        String base64File = Base64.getEncoder().encodeToString(file.getBytes());

        JSONObject payload = new JSONObject();
        payload.put("req_id", reqId);
        payload.put("doc_base64", base64File);
        payload.put("doc_type", docType);
        payload.put("isIOS", isIOS);
        payload.put("orientation", orientation);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl + "/api/v1/deepfake-detection/" + docType);

        httpPost.setHeader("token", apiToken);
        httpPost.setHeader("Content-Type", "application/json");

        StringEntity entity = new StringEntity(payload.toString());
        httpPost.setEntity(entity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = new JSONObject(responseBody);
            if (jsonResponse.getBoolean("success")) {
                return "The image is " + jsonResponse.getString("result");
            } else {
                return "Error: " + jsonResponse.getString("error_message");
            }
        }
    }
}
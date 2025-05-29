package com.IS336.PROJECT.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeepFakeResponse {
    private String req_id;
    private boolean success;
    private String error_message;
    private String doc_type;
    private String result;
    
}

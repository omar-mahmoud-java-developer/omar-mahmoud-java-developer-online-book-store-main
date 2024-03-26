package com.othman.onlinebookstore.DTO;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String email;
    private String token;
}

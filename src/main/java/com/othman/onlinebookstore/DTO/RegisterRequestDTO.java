package com.othman.onlinebookstore.DTO;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}

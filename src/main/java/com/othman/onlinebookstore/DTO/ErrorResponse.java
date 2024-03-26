package com.othman.onlinebookstore.DTO;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

// Custom error response DTO
@Data
@AllArgsConstructor
public class ErrorResponse {
    private final int statusCode;
    private final String error;
    private final String message;

    // Default constructor
    public ErrorResponse() {
        this.statusCode = HttpStatus.OK.value();
        this.error = "";
        this.message = "";
    }


}
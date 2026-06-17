package com.bank.loans.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        description = "Error Response Data Transfer Object"
)
public record ErrorResponseDTO(
        @Schema(description = "API path where the error occurred")
        String apiPath,
        @Schema(description = "HTTP status code of the error")
        HttpStatus errorCode,
        @Schema(description = "Detailed error message")
        String errorMessage,
        @Schema(description = "Timestamp when the error occurred")
        LocalDateTime errorTime) {
}


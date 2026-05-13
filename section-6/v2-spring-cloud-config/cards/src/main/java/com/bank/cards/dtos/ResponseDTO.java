package com.bank.cards.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Response Data Transfer Object"
)
public record ResponseDTO(

        @Schema(description = "Status code of the response")
        String statusCode,
        @Schema(description = "Status message of the response")
        String statusMsg) {
}


package com.bank.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(
        description = "Account Data Transfer Object",
        example = """
                {
                    "accountNumber": "1234567890",
                    "accountType": "SAVINGS",
                    "branchAdress": "123 Main St, Anytown, USA"
                }
                """
)
public record AccountDTO(
        @NotEmpty(message = "Mobile number cannot be empty")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "Invalid mobile number format")
        Long accountNumber,

        @NotBlank(message = "Account type cannot be blank")
        String accountType,
        @NotBlank (message = "Branch address cannot be blank")
        String branchAdress) {

}

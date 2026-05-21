package com.bank.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(
        description = "Customer Data Transfer Object",
        example = """
                {
                    "name": "John Doe",
                    "email": "johndoe@gmail.com",
                    "mobileNumber": "1234567890"
                }
                """
)
public record CustomerDTO(
        @NotBlank(message = "Name cannot be blank")
        @Size(min=3,max = 30, message = "The length of the name must be between 3 and 30 characters")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be empty")
        String email,

        @NotEmpty(message = "Mobile number cannot be empty")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "Invalid mobile number format")
        String mobileNumber) {
}

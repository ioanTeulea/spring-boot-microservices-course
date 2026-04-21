package com.bank.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Customer Details Data Transfer Object",
        example = """
                {
                    "customer": {
                        "name": "John Doe",
                        "email": "johndoe@gmail,com",
                        "mobileNumber": "1234567890"
                    },
                    "account": {
                        "accountNumber": "1234567890",
                        "accountType": "SAVINGS",
                        "branchAdress": "123 Main St, Anytown, USA"
                    }
                }
                """
)
public record CustomerDetailsDTO(
        CustomerDTO customer,
        AccountDTO account
) {}

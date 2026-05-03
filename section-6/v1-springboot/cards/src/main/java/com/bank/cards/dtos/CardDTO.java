package com.bank.cards.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

@Schema(name = "CardDTO",
        description = "Schema to hold Card information"
)
public record CardDTO(

        @NotBlank(message = "Mobile Number can not be a null or empty")
        @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
        @Schema(description = "Mobile Number of Customer", example = "4354437687")
        String mobileNumber,

        @NotBlank(message = "Card Number can not be a null or empty")
        @Pattern(regexp="(^$|[0-9]{16})",message = "CardNumber must be 16 digits")
        @Schema(description = "Card Number of the customer", example = "4006469303415732")
        String cardNumber,
        @NotBlank(message = "CardType can not be a null or empty")
        @Schema(description = "Type of the card", example = "Credit Card")
        String cardType,
        @Positive(message = "Total card limit should be greater than zero")
        @Schema(description = "Total amount limit available against a card", example = "100000")
        Integer totalLimit,

        @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
        @Schema(description = "Total amount used by a Customer", example = "1000")
        Integer amountUsed,
        @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
        @Schema(description = "Total available amount against a card", example = "90000")
        Integer availableAmount
) {
}

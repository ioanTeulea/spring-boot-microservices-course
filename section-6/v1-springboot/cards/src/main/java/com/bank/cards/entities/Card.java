package com.bank.cards.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tools.jackson.core.ObjectReadContext;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Column(name="mobile_number")
    private String mobileNumber;

    @Pattern(regexp = "\\d{16}", message = "Card number must have exactly 16 digits")
    @Column(name="card_number")
    private String cardNumber;

    @Column(name="card_type")
    private String cardType;

    @Column(name="total_limit")
    private int totalLimit;

    @Column(name="amount_used")
    private int amountUsed;

    @Column(name="available_amount")
    private int availableAmount;

}

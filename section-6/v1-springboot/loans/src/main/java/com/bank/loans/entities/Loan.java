package com.bank.loans.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loan_id")
    private Long loanId;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="loan_number")
    private String loanNumber;

    @Column(name="loan_type")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Column(name="total_loan")
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Column(name="amount_paid")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Column(name="outstanding_amount")
    private int outstandingAmount;


}

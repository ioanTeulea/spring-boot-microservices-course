package com.bank.accounts.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{

    @Id
    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="customer_id")
    @NotNull
    private Long customerId;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAdress;

}

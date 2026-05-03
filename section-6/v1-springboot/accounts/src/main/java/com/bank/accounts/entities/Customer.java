package com.bank.accounts.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @NotBlank
    @Column(name="name")
    @Size(min=3,max = 30, message = "The length of the name must be between 3 and 30 characters")
    private String name;

    @Email
    @NotBlank
    @Column(name="email")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    @Column(name="mobile_number")
    private String mobileNumber;

}

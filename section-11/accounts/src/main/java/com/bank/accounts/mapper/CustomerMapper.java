package com.bank.accounts.mapper;

import com.bank.accounts.dtos.*;
import com.bank.accounts.entities.Account;
import com.bank.accounts.entities.Customer;

public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
    }
    public static Customer toCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.name())
                .email(customerDTO.email())
                .mobileNumber(customerDTO.mobileNumber())
                .build();
    }

    public static CustomerFullDetailsDTO toCustomerFullDetailsDTO(
            Customer customer,
            AccountDTO accountDTO,
            CardDTO cardDTO,
            LoanDTO loanDTO){

        return new CustomerFullDetailsDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                accountDTO,
                cardDTO,
                loanDTO
        );

    }
}

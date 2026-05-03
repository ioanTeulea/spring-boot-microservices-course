package com.bank.accounts.mapper;

import com.bank.accounts.dtos.CustomerDTO;
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
}

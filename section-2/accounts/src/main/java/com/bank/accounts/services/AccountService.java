package com.bank.accounts.services;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dtos.CustomerDTO;
import com.bank.accounts.entities.Account;
import com.bank.accounts.entities.Customer;
import com.bank.accounts.exceptions.CustomerAlreadyExistsException;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repositories.AccountRepository;
import com.bank.accounts.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO);
        if(customerRepository.existsByMobileNumber(customer.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("Customer with this mobile number already exists");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        accountRepository.save(Account.builder()
                .accountNumber(Long.parseLong(savedCustomer.getMobileNumber())+new Random().nextInt(1000))
                .customerId(savedCustomer.getCustomerId())
                .accountType(AccountsConstants.SAVINGS)
                .branchAdress(AccountsConstants.ADDRESS)
                .createdAt(LocalDateTime.now())
                .createdBy("anonymous")
                .build());
    }
}

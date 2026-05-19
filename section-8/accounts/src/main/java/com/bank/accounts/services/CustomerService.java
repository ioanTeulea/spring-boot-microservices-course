package com.bank.accounts.services;

import com.bank.accounts.dtos.CardDTO;
import com.bank.accounts.dtos.CustomerFullDetailsDTO;
import com.bank.accounts.dtos.LoanDTO;
import com.bank.accounts.dtos.ResponseDTO;
import com.bank.accounts.entities.Account;
import com.bank.accounts.entities.Customer;
import com.bank.accounts.exceptions.ResourceNotFoundException;
import com.bank.accounts.mapper.AccountMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repositories.AccountRepository;
import com.bank.accounts.repositories.CustomerRepository;
import com.bank.accounts.services.client.CardsFeignClient;
import com.bank.accounts.services.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerFullDetailsDTO fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getName(),"mobileNumber",mobileNumber));

        Account account=accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(Account.class.getName(),"customerId",String.valueOf(customer.getCustomerId())));
        ResponseEntity<CardDTO> card=cardsFeignClient.fetchCardDetails(mobileNumber);
        ResponseEntity<LoanDTO> loan=loansFeignClient.fetchLoanDetails(mobileNumber);

        return CustomerMapper.toCustomerFullDetailsDTO(
                customer,
                AccountMapper.mapToAccountDto(account),
                card.getBody(),
                loan.getBody());

    }
}

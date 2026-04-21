package com.bank.accounts.services;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dtos.AccountDTO;
import com.bank.accounts.dtos.CustomerDTO;
import com.bank.accounts.dtos.CustomerDetailsDTO;
import com.bank.accounts.entities.Account;
import com.bank.accounts.entities.Customer;
import com.bank.accounts.exceptions.CustomerAlreadyExistsException;
import com.bank.accounts.exceptions.InvalidAccountOwnershipException;
import com.bank.accounts.exceptions.ResourceNotFoundException;
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

        Customer savedCustomer=customerRepository.save(customer);
        accountRepository.save(Account.builder()
                .accountNumber(Long.parseLong(savedCustomer.getMobileNumber())+new Random().nextInt(1000))
                .customerId(savedCustomer.getCustomerId())
                .accountType(AccountsConstants.SAVINGS)
                .branchAdress(AccountsConstants.ADDRESS)
                .build());
    }

    @Override
    public CustomerDetailsDTO fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getName(),"mobileNumber",mobileNumber));

        Account account=accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(Account.class.getName(),"customerId",String.valueOf(customer.getCustomerId())));

        CustomerDTO customerDTO = new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );

        AccountDTO accountDTO = new AccountDTO(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBranchAdress()
        );

        return new CustomerDetailsDTO(customerDTO, accountDTO);


    }

    @Override
    public boolean updateAccount(CustomerDetailsDTO dto) {

        if (dto.customer() != null && dto.account() != null) {

            Customer customer = customerRepository.findByMobileNumber(dto.customer().mobileNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            Customer.class.getName(),
                            "mobileNumber",
                            dto.customer().mobileNumber()
                    ));

            Account account = accountRepository.findById(dto.account().accountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            Account.class.getName(),
                            "accountNumber",
                            String.valueOf(dto.account().accountNumber())
                    ));

            if (!account.getCustomerId().equals(customer.getCustomerId())) {
                throw new InvalidAccountOwnershipException("Account does not belong to the given customer");
            }

            customer.setName(dto.customer().name());
            customer.setEmail(dto.customer().email());
            customerRepository.save(customer);

            account.setAccountType(dto.account().accountType());
            account.setBranchAdress(dto.account().branchAdress());
            accountRepository.save(account);

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getName(),"mobileNumber",mobileNumber));

        Account account=accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(Account.class.getName(),"customerId",String.valueOf(customer.getCustomerId())));

        accountRepository.delete(account);
        return true;

    }
}

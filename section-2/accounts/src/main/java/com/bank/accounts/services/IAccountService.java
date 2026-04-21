package com.bank.accounts.services;

import com.bank.accounts.dtos.CustomerDTO;
import com.bank.accounts.dtos.CustomerDetailsDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDetailsDTO fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDetailsDTO customerDetailsDTO);

    boolean deleteAccount(String mobileNumber);

}

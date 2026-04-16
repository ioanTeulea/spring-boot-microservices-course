package com.bank.accounts.services;

import com.bank.accounts.dtos.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);
}

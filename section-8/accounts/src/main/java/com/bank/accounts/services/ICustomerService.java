package com.bank.accounts.services;

import com.bank.accounts.dtos.CustomerFullDetailsDTO;

public interface ICustomerService {
    CustomerFullDetailsDTO fetchCustomerDetails(String mobileNumber);
}

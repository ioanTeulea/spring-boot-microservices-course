package com.bank.loans.services;

import com.bank.loans.dtos.LoanDTO;

public interface ILoanService {
    void createLoan(String mobileNumber);
    LoanDTO fetchLoan(String mobileNumber);
    void updateLoan(LoanDTO loansDto);

    void deleteLoan(String mobileNumber);
}

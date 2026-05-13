package com.bank.loans.services;


import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dtos.LoanDTO;
import com.bank.loans.entities.Loan;
import com.bank.loans.exceptions.LoanAlreadyExistsException;
import com.bank.loans.exceptions.ResourceNotFoundException;
import com.bank.loans.mapper.LoanMapper;
import com.bank.loans.repositories.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {
    private final LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans= loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
       return LoanMapper.mapToLoanDto(loan);
    }

    @Override
    public void updateLoan(LoanDTO loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.loanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDto.loanNumber()));

        loan.setMobileNumber(loanDto.mobileNumber());
        loan.setLoanType(loanDto.loanType());
        loan.setTotalLoan(loanDto.totalLoan());
        loan.setAmountPaid(loanDto.amountPaid());
        loan.setOutstandingAmount(loanDto.outstandingAmount());

        loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(String mobileNumber) {
        Loan loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepository.deleteById(loans.getLoanId());
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}

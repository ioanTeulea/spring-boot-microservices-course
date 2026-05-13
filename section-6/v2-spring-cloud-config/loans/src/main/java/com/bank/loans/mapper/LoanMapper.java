package com.bank.loans.mapper;

import com.bank.loans.dtos.LoanDTO;
import com.bank.loans.entities.Loan;

public class LoanMapper {

    public static LoanDTO mapToLoanDto(Loan loan) {
        return new LoanDTO(
                loan.getMobileNumber(),
                loan.getLoanNumber(),
                loan.getLoanType(),
                loan.getTotalLoan(),
                loan.getAmountPaid(),
                loan.getOutstandingAmount()
        );
    }
    public static Loan mapToLoan(LoanDTO loanDTO) {
        return Loan.builder()
                .mobileNumber(loanDTO.mobileNumber())
                .loanNumber(loanDTO.loanNumber())
                .loanType(loanDTO.loanType())
                .totalLoan(loanDTO.totalLoan())
                .amountPaid(loanDTO.amountPaid())
                .outstandingAmount(loanDTO.outstandingAmount())
                .build();
    }
}

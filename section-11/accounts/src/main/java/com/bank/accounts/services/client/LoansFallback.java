package com.bank.accounts.services.client;

import com.bank.accounts.dtos.LoanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoanDTO> fetchLoanDetails(String mobileNumber, String correlationId) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(null);
    }
}

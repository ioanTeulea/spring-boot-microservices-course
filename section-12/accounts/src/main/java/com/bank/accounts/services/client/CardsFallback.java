package com.bank.accounts.services.client;

import com.bank.accounts.dtos.CardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<CardDTO> fetchCardDetails(String mobileNumber, String correlationId) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(null);
    }
}

package com.bank.accounts.services.client;

import com.bank.accounts.dtos.CardDTO;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping("/api/fetch")
    public ResponseEntity<CardDTO> fetchCardDetails(@RequestParam String mobileNumber) ;

}

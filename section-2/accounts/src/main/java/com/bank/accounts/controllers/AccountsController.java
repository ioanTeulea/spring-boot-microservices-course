package com.bank.accounts.controllers;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dtos.CustomerDTO;
import com.bank.accounts.dtos.ResponseDTO;
import com.bank.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
@AllArgsConstructor
public class AccountsController {

    private final IAccountService accountService;

    @PostMapping(path="/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201
                ));
    }

}

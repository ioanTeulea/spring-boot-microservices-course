package com.bank.accounts.controllers;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dtos.CustomerDTO;
import com.bank.accounts.dtos.CustomerDetailsDTO;
import com.bank.accounts.dtos.ErrorResponseDTO;
import com.bank.accounts.dtos.ResponseDTO;
import com.bank.accounts.services.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST API for Accounts", description = "Endpoints for managing bank accounts")
@RestController
@RequestMapping(path="/api")
@AllArgsConstructor
public class AccountsController {

    private final IAccountService accountService;

    @Operation(summary = "Create a new bank account", description = "Endpoint to create a new bank account with customer details")
    @ApiResponses({
            @ApiResponse( responseCode = "201", description = "HTTP Status Created"),
            @ApiResponse(responseCode = "400", description = "Customer already exists", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(path="/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201
                ));
    }

    @Operation(summary = "Fetch account details", description = "Endpoint to fetch account details using mobile number")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(path="/fetch")
    public ResponseEntity<CustomerDetailsDTO> fetchAccountDetails(
            @RequestParam
            @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "Invalid mobile number format") String mobileNumber) {
        CustomerDetailsDTO customer=accountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);
    }

    @Operation(summary = "Update account details", description = "Endpoint to update existing account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "HTTP Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))

    })
    @PutMapping(path="/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDetailsDTO customerDTO) {
        boolean isUpdated=accountService.updateAccount(customerDTO);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(
                            AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200
                    ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(
                            AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_UPDATE
                    ));
        }
    }

    @Operation(summary = "Delete an account", description = "Endpoint to delete an account using mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "HTTP Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping(path="/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @RequestParam
            @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "Invalid mobile number format") String mobileNumber) {
        boolean isDeleted=accountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(
                            AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200
                    ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(
                            AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_DELETE
                    ));
        }
    }



}

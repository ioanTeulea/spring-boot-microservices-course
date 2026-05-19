package com.bank.accounts.controllers;

import com.bank.accounts.dtos.CustomerDetailsDTO;
import com.bank.accounts.dtos.CustomerFullDetailsDTO;
import com.bank.accounts.dtos.ErrorResponseDTO;
import com.bank.accounts.services.ICustomerService;
import feign.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CRUD REST API for Customer", description = "Endpoints for managing bank customers and their details")
@RestController
@RequestMapping(path="/api")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;


    @Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch customer details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number format", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerFullDetailsDTO> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "Invalid mobile number format") String mobileNumber) {
        CustomerFullDetailsDTO customerFullDetailsDTO = customerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerFullDetailsDTO);
    }
}

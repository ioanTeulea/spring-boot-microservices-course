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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST API for Customer", description = "Endpoints for managing bank customers and their details")
@RestController
@RequestMapping(path="/api")
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
            @RequestHeader("bank-correlation-id") String correlationId,
            @RequestParam
            @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "Invalid mobile number format") String mobileNumber) {

        logger.debug("fetchCustomerDetails method start");
        CustomerFullDetailsDTO customerFullDetailsDTO = customerService.fetchCustomerDetails(mobileNumber,correlationId);
        logger.debug("fetchCustomerDetails method end");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerFullDetailsDTO);
    }
}

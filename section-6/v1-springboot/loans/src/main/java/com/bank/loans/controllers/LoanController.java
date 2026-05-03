package com.bank.loans.controllers;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dtos.ErrorResponseDTO;
import com.bank.loans.dtos.LoanDTO;
import com.bank.loans.dtos.LoansContactInfoDTO;
import com.bank.loans.dtos.ResponseDTO;
import com.bank.loans.services.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "CRUD REST APIs for Loans",
        description = "Endpoints for managing loans associated with bank accounts"
)
@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class LoanController {
    private final ILoanService loanService;

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;

    private final LoansContactInfoDTO loansContactInfoDTO;


    @Operation(summary = "Create Loan REST API", description = "REST API to create new loan inside EazyBank")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Loan already exists", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Loan Details REST API", description = "REST API to fetch loan details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        LoanDTO loansDto = loanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(summary = "Update Loan Details REST API", description = "REST API to update loan details based on a loan number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoanDTO loanDto) {
         loanService.updateLoan(loanDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));

    }

    @Operation(summary = "Delete Loan Details REST API", description = "REST API to delete Loan details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        loanService.deleteLoan(mobileNumber);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));

    }

    @Operation(summary = "Get build information", description = "Endpoint to get loans service build version information")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(path="/version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(summary = "Get java version", description = "Endpoint to get loans service java build version information")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(path="/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(summary = "Get contact info", description = "Endpoint to get loan contact information")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(path="/contact-info")
    public ResponseEntity<LoansContactInfoDTO> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDTO);
    }
}

package com.bank.accounts.dtos;

public record AccountDTO(
        Long accountNumber,
        String accountType,
        String branchAdress) {

}

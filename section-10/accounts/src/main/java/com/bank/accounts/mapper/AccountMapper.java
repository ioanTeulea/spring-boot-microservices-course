package com.bank.accounts.mapper;

import com.bank.accounts.dtos.AccountDTO;
import com.bank.accounts.entities.Account;

public class AccountMapper {

    public static AccountDTO mapToAccountDto(Account account) {
        return new AccountDTO(
               account.getAccountNumber(),
                account.getAccountType(),
                account.getBranchAdress()
        );
    }
    public static Account mapToAccountEntity(AccountDTO accountDTO) {
        return Account.builder()
                .accountNumber(accountDTO.accountNumber())
                .accountType(accountDTO.accountType())
                .branchAdress(accountDTO.branchAdress())
                .build();
    }
}

package com.finalproject.internet.banking.internetbanking.dtos;

import java.math.BigDecimal;

import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CheckAccountDTO {
    @NotBlank(message = "Código de conta é obrigatório!")
    private String     accountNum;
    @NotBlank(message = "Código de agência é obrigatório!")
    private String     branch;
    @NotBlank(message = "Saldo de conta é obrigatório!")
    private BigDecimal balance;
    private User       user;

// CONSTRUCTORS
    public CheckAccountDTO() {}
    
    public CheckAccountDTO(CheckAccount account) {
        this.accountNum = account.getAccountNum();
        this.branch     = account.getBranch();
        this.balance    = account.getBalance();
        this.user       = account.getUser();
    }
}

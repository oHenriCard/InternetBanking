package com.finalproject.internet.banking.internetbanking.dtos;

import jakarta.validation.constraints.NotBlank;

public class StatementDTO {
    @NotBlank(message = "O número da conta é obrigatória!")
    private String accountNum;

// GETTER N SETTER
    public String getAccountNum() {
        return this.accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
}

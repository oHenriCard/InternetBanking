package com.finalproject.internet.banking.internetbanking.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DepositDTO {
    @NotBlank(message = "O número da conta é obrigatória!")
    private String accountNum;

    @NotNull(message = "O valor é obrigatório!")
    @Positive(message = "O valor do depósito precisa ser maior que zero.")
    private BigDecimal value;

// GETTERS N SETTERS
    public String getAccountNum() {
        return this.accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public BigDecimal getValue() {
        return this.value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

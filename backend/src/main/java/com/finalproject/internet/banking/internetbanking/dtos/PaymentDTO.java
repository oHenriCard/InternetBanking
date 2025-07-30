package com.finalproject.internet.banking.internetbanking.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentDTO {
    @NotBlank(message = "O número da conta é obrigatório!")
    private String accountNum;

    @NotNull(message = "O valor é obrigatório!")
    @Positive(message = "O valor de pagamento precisa ser maior que zero.")
    private BigDecimal value;
    
    @NotNull(message = "Digite uma descrição.")
    private String description;

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

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

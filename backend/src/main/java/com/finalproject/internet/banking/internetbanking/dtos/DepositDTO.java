package com.finalproject.internet.banking.internetbanking.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class DepositDTO {
    @NotBlank(message = "O número da conta é obrigatória!")
    private String accountNum;

    @NotNull(message  = "O valor é obrigatório!")
    @Positive(message = "O valor do depósito precisa ser maior que zero.")
    private BigDecimal value;
}

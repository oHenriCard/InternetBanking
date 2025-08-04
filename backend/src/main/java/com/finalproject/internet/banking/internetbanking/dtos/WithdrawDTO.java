package com.finalproject.internet.banking.internetbanking.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WithdrawDTO{
    @NotBlank(message = "O número da conta é obrigatória!")
    private String accountNum;

    @NotNull(message = "O valor é obrigatório!")
    private BigDecimal value;
}

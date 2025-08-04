package com.finalproject.internet.banking.internetbanking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.finalproject.internet.banking.internetbanking.entities.Operation;

import lombok.Getter;

@Getter
public class OperationDTO {
    private final Operation.type type;
    private final BigDecimal     value;
    private final String         description;
    private final LocalDateTime  dateTime;

    public OperationDTO(Operation operation) {
        this.type        = operation.getType();
        this.value       = operation.getValue();
        this.description = operation.getDescription();
        this.dateTime    = operation.getDateTime();
    }
}

package com.example.restApi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DebtRequestDTO {

    private Double amount;
    private String currency;
    private LocalDate dueDate;
    private Long customerId;
}

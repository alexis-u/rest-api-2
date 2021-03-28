package com.example.restApi.service;

import com.example.restApi.dto.DebtRequestDTO;
import com.example.restApi.exception.RestServiceException;
import com.example.restApi.model.Debt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DebtService {

    public Debt createDebt(Double amount, String currency, LocalDate dueDate, Long customerId) throws Exception {
        Debt debt = new Debt();
        debt.setAmount(validateAmount(amount));
        debt.setCurrency(validateCurrency(currency));
        debt.setDueDate(validateDueDate(dueDate));
        debt.setCustomerId(customerId);

        return debt;
    }

    private Double validateAmount(Double amount) throws Exception {
        if (amount != null) {
            if (amount > 0.0)
                return amount;
            else
                throw new RestServiceException("Amount value cannot be negative or null");
        } else
            throw new RestServiceException("Amount value cannot be empty");
    }

    private String validateCurrency(String currency) throws Exception {
        if (currency != null && !currency.isEmpty())
            if (currency.length() >= 3)
                return currency.substring(0, 3).toUpperCase();
            else
                throw new RestServiceException("Currency must be 3 symbols at least");
        else
            throw new RestServiceException("Currency cannot be empty");
    }

    private LocalDate validateDueDate(LocalDate date) throws Exception {
        if (date != null) {
            LocalDate today = LocalDate.now();
            if (date.compareTo(today) >= 0)
                return date;
            else
                throw new RestServiceException("DueDate {" + date + "} cannot be earlier than current day");
        } else
            throw new RestServiceException("DueDate cannot be empty");
    }

    public void validateDebt(DebtRequestDTO debtRequestDTO) throws Exception {
        validateAmount(debtRequestDTO.getAmount());
        validateCurrency(debtRequestDTO.getCurrency());
        validateDueDate(debtRequestDTO.getDueDate());
    }
}

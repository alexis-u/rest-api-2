package com.example.restApi.service;

import com.example.restApi.dto.DebtRequestDTO;
import com.example.restApi.exception.RestServiceException;
import com.example.restApi.model.Debt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DebtServiceTests {

    @InjectMocks
    DebtService debtService;

    @Test
    public void createDebtTest() throws Exception {
        LocalDate today = LocalDate.now();
        Debt debt = debtService.createDebt(46.2, "USD", today, 39L);

        assertEquals(46.2, debt.getAmount());
        assertEquals("USD", debt.getCurrency());
        assertEquals(today, debt.getDueDate());
        assertEquals(39L, debt.getCustomerId());
    }

    @Test
    public void validateDebtTest() throws Exception {
        DebtRequestDTO debt = new DebtRequestDTO();
        debt.setAmount(41.54);
        debt.setCurrency("EUR");
        debt.setDueDate(LocalDate.now());
        debt.setCustomerId(1245L);
        debtService.validateDebt(debt);
    }

    @Test
    public void validateDebtFailureTest() {
        DebtRequestDTO debt = new DebtRequestDTO();
        debt.setAmount(41.54);
        debt.setDueDate(LocalDate.now().minusMonths(5));
        debt.setCustomerId(1245L);
        assertThrows(RestServiceException.class, () ->
                debtService.validateDebt(debt)
        );
    }
}

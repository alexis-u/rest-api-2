package com.example.restApi.controller;

import com.example.restApi.dto.DebtRequestDTO;
import com.example.restApi.exception.RestServiceException;
import com.example.restApi.model.Customer;
import com.example.restApi.model.Debt;
import com.example.restApi.repository.CustomerRepository;
import com.example.restApi.repository.DebtRepository;
import com.example.restApi.service.DebtService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class DebtControllerTests {

    @InjectMocks
    DebtController debtController;

    @Mock
    CustomerRepository customerRepository;
    @Mock
    DebtRepository debtRepository;
    @Mock
    DebtService debtService;

    Debt debt = buildDebt();
    DebtRequestDTO debtDTO = buildDebtRequestDTO();

    @Test
    public void findAllDebtsTest() {
        when(debtRepository.findAll()).thenReturn(getDebts());
        List<Debt> result = (List<Debt>) debtController.findAllDebts();

        assertEquals(2, result.size());
        log.info("findAllDebtsTest:\n" + result);
    }

    @Test
    public void findDebtTest() throws RestServiceException {
        when(debtRepository.findById(3L)).thenReturn(Optional.ofNullable(debt));
        Debt result = debtController.findDebt(3L);

        assertNotNull(result);
        log.info("findDebtTest:\n" + result.toString());
    }

    @Test
    public void findDebtFailureTest() {
        when(debtRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(RestServiceException.class, () ->
                debtController.findDebt(4L)
        );
    }

    @Test
    public void addDebtTest() throws Exception {
        when(customerRepository.findById(4L)).thenReturn(Optional.of(new Customer()));
        debtController.addDebt(debtDTO);
    }

    @Test
    public void addDebtFailureTest() {
        when(customerRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(RestServiceException.class, () ->
                debtController.addDebt(debtDTO)
        );
    }

    @Test
    public void editDebtTest() throws Exception {
        when(debtRepository.findById(anyLong())).thenReturn(Optional.ofNullable(debt));
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer()));

        debtController.editDebt(debtDTO, anyLong());
    }

    @Test
    public void deleteDebtTest() throws RestServiceException {
        when(debtRepository.findById(3L)).thenReturn(Optional.ofNullable(debt));
        debtController.deleteDebt(3L);
    }

    private Debt buildDebt() {
        Debt debt = new Debt();
        debt.setId(3L);
        debt.setAmount(12.00);
        debt.setCurrency("AUD");
        debt.setDueDate(LocalDate.now());
        debt.setCustomerId(125L);

        return debt;
    }

    private DebtRequestDTO buildDebtRequestDTO() {
        DebtRequestDTO debtDTO = new DebtRequestDTO();
        debtDTO.setCustomerId(4L);
        debtDTO.setDueDate(LocalDate.now());
        debtDTO.setCurrency("EUR");
        debtDTO.setAmount(421.0234);

        return debtDTO;
    }

    private List<Debt> getDebts() {
        List<Debt> list = new ArrayList<>();
        list.add(debt);
        list.add(debt);
        return list;
    }
}

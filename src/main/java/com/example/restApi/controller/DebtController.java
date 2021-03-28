package com.example.restApi.controller;

import com.example.restApi.dto.DebtRequestDTO;
import com.example.restApi.exception.RestServiceException;
import com.example.restApi.model.Customer;
import com.example.restApi.model.Debt;
import com.example.restApi.repository.CustomerRepository;
import com.example.restApi.repository.DebtRepository;
import com.example.restApi.service.CustomerService;
import com.example.restApi.service.DebtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class DebtController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DebtService debtService;
    @Autowired
    private DebtRepository debtRepository;

    @PostMapping(value = "/addDebt")
    public void addDebt(@RequestBody DebtRequestDTO debtRequestDTO) throws Exception {
        Optional<Customer> customer = customerRepository.findById(debtRequestDTO.getCustomerId());
        if (customer.isPresent()) {
            Debt debt = debtService.createDebt(
                    debtRequestDTO.getAmount(),
                    debtRequestDTO.getCurrency(),
                    debtRequestDTO.getDueDate(),
                    debtRequestDTO.getCustomerId());

            debtRepository.save(debt);
        } else
            throw new RestServiceException("addDebt: Customer with id:"
                    + debtRequestDTO.getCustomerId() + " doesn't exist!");
    }

    @GetMapping(value = "/findDebt/{id}")
    public Debt findDebt(@PathVariable("id") Long id) throws RestServiceException {
        Optional<Debt> debt = debtRepository.findById(id);
        if (debt.isPresent()) {
            return debt.get();
        } else
            throw new RestServiceException("findDebt: Debt with id:" + id + " doesn't exist!");
    }

    @GetMapping(value = "/debts")
    public Iterable<Debt> findAllDebts() {
        return debtRepository.findAll();
    }

    @PatchMapping(value = "/editDebt/{id}")
    public void editDebt(@RequestBody DebtRequestDTO debtRequestDTO, @PathVariable("id") Long id)
            throws Exception {
        Optional<Debt> debtOptional = debtRepository.findById(id);
        if (debtOptional.isPresent()) {
            Debt debt = debtOptional.get();
            debtService.validateDebt(debtRequestDTO);
            Optional<Customer> customerOptional = customerRepository.findById(debtRequestDTO.getCustomerId());
            if (customerOptional.isPresent()) {
                debt.setAmount(debtRequestDTO.getAmount());
                debt.setCurrency(debtRequestDTO.getCurrency());
                debt.setDueDate(debtRequestDTO.getDueDate());
                debt.setCustomerId(debtRequestDTO.getCustomerId());

                debtRepository.save(debt);
            } else
                throw new RestServiceException("editDebt: Customer with id:"
                        + debtRequestDTO.getCustomerId() + " doesn't exist!");
        } else
            throw new RestServiceException("editDebt: Debt with id:" + id + " doesn't exist!");
    }

    @DeleteMapping(value = "/deleteDebt/{id}")
    public void deleteDebt(@PathVariable("id") Long id) throws RestServiceException {
        Optional<Debt> debt = debtRepository.findById(id);
        if (debt.isPresent()) {
            debtRepository.deleteById(id);
            log.info("Debt with id:{} successfully deleted", id);
        } else
            throw new RestServiceException("deleteDebt: Debt with id:" + id + " does not exist!");
    }
}

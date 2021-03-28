package com.example.restApi.controller;

import com.example.restApi.dto.CustomerRequestDTO;
import com.example.restApi.exception.RestServiceException;
import com.example.restApi.model.Customer;
import com.example.restApi.repository.CustomerRepository;
import com.example.restApi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class CustomerControllerTests {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerService customerService;

    Customer customer = buildCustomer();
    CustomerRequestDTO customerDTO = buildCustomerRequestDTO();

    @Test
    public void findAllCustomersTest() {
        when(customerRepository.findAll()).thenReturn(getCustomers());
        List<Customer> result = (List<Customer>) customerController.findAllCustomers();

        assertEquals(2, result.size());
        log.info("findAllCustomersTest:\n" + result);
    }

    @Test
    public void findCustomerTest() throws RestServiceException {
        when(customerRepository.findById(12L)).thenReturn(Optional.ofNullable(customer));
        Customer customer = customerController.findCustomer(12L);

        assertNotNull(customer);
        log.info("findCustomerTest:\n" + customer.toString());
    }

    @Test
    public void findCustomerFailureTest() {
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RestServiceException.class, () ->
                customerController.findCustomer(2L)
        );
    }

    @Test
    public void addCustomerTest() throws RestServiceException {
        when(customerRepository.findByEmail("sad@gmail.com")).thenReturn(Optional.empty());
        customerController.addCustomer(customerDTO);
    }

    @Test
    public void addCustomerFailureTest() {
        when(customerRepository.findByEmail("sad@gmail.com")).thenReturn(Optional.of(new Customer()));

        assertThrows(RestServiceException.class, () ->
                customerController.addCustomer(customerDTO)
        );
    }

    @Test
    public void editCustomerTest() throws RestServiceException {
        when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(customer));
        customerController.editCustomer(customerDTO, anyLong());
    }

    @Test
    public void deleteCustomerTest() throws RestServiceException {
        when(customerRepository.findById(12L)).thenReturn(Optional.ofNullable(customer));
        customerController.deleteCustomer(12L);
    }

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setId(12L);
        customer.setFirstName("Peter");
        customer.setLastName("Jackson");
        customer.setCountry("Germany");
        customer.setEmail("jakp@gmail.com");
        customer.setPassword("Jackson");

        return customer;
    }

    private CustomerRequestDTO buildCustomerRequestDTO() {
        CustomerRequestDTO customerDTO = new CustomerRequestDTO();
        customerDTO.setFirstName("Said");
        customerDTO.setLastName("Parker");
        customerDTO.setCountry("Spain");
        customerDTO.setEmail("sad@gmail.com");
        customerDTO.setPassword("sa2d1");

        return customerDTO;
    }

    private List<Customer> getCustomers() {
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        list.add(customer);
        return list;
    }
}

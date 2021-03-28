package com.example.restApi.service;

import com.example.restApi.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @InjectMocks
    CustomerService customerService;

    @Test
    public void createCustomerTest() {
        Customer customer = customerService
                .createCustomer(
                        "Alex",
                        "Dee",
                        "France",
                        "ad311@gmail.com",
                        "smthsmthpw2");

        assertEquals("Alex", customer.getFirstName());
        assertEquals("Dee", customer.getLastName());
        assertEquals("France", customer.getCountry());
        assertEquals("ad311@gmail.com", customer.getEmail());
        assertNotNull(customer.getPassword());
    }

}

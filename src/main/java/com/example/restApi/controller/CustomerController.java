package com.example.restApi.controller;

import com.example.restApi.dto.CustomerRequestDTO;
import com.example.restApi.exception.RestServiceException;
import com.example.restApi.model.Customer;
import com.example.restApi.repository.CustomerRepository;
import com.example.restApi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/addCustomer")
    public void addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) throws RestServiceException {

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerRequestDTO.getEmail());
        if (optionalCustomer.isPresent()) {
            throw new RestServiceException("addCustomer: Email "
                    + customerRequestDTO.getEmail() + " already in use!");
        }
        Customer customer = customerService.createCustomer(
                customerRequestDTO.getFirstName(),
                customerRequestDTO.getLastName(),
                customerRequestDTO.getCountry(),
                customerRequestDTO.getEmail(),
                customerRequestDTO.getPassword());

        customerRepository.save(customer);
    }

    @GetMapping(value = "/findCustomer/{id}")
    public Customer findCustomer(@PathVariable("id") Long id) throws RestServiceException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new RestServiceException("findCustomer: Customer with id:" + id + " doesn't exist!");
    }

    @GetMapping(value = "/customers")
    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @PatchMapping(value = "/editCustomer/{id}")
    public void editCustomer(@RequestBody CustomerRequestDTO customerRequestDTO, @PathVariable("id") Long id)
            throws RestServiceException {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            customer.setFirstName(customerRequestDTO.getFirstName());
            customer.setLastName(customerRequestDTO.getLastName());
            customer.setCountry(customerRequestDTO.getCountry());
            customer.setEmail(customerRequestDTO.getEmail());
            customer.setPassword(customerRequestDTO.getPassword());

            customerRepository.save(customer);
        } else
            throw new RestServiceException("editCustomer: Customer with id:" + id + " does not exist!");
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) throws RestServiceException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            log.info("Customer with id:{} successfully deleted", id);
        } else
            throw new RestServiceException("deleteCustomer: Customer with id:" + id + " does not exist!");
    }
}

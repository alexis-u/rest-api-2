package com.example.restApi.service;

import com.example.restApi.model.Customer;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer createCustomer(String firstName,
                                   String lastName,
                                   String country,
                                   String email,
                                   String password) {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCountry(country);
        customer.setEmail(email);
        customer.setPassword(encodePassword(password));

        return customer;
    }

//    Considering that decoder is not required
    private String encodePassword(String password) {
        byte[] pwBytes = DigestUtils.sha(password);
        return Base64.encodeBase64String(pwBytes);
    }

}

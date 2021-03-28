package com.example.restApi.dto;

import lombok.Data;

@Data
public class CustomerRequestDTO {

    private String firstName;
    private String lastName;
    private String country;
    private String email;
    private String password;
}

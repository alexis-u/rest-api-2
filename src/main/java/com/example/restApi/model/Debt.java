package com.example.restApi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "debts")
@Data
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;
    @Column(name = "customerId", nullable = false)
    private Long customerId;

}

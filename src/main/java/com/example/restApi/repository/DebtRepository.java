package com.example.restApi.repository;

import com.example.restApi.model.Debt;
import org.springframework.data.repository.CrudRepository;

public interface DebtRepository extends CrudRepository<Debt, Long> {
}

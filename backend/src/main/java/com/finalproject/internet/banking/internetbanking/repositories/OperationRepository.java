package com.finalproject.internet.banking.internetbanking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.internet.banking.internetbanking.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{
    List<Operation> findByAccountAccountNumOrderByDateTimeDesc(String accountNum);
}

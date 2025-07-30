package com.finalproject.internet.banking.internetbanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.internet.banking.internetbanking.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

}

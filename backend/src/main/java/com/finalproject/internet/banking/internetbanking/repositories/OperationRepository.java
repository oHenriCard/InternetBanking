package com.finalproject.internet.banking.internetbanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.internet.banking.internetbanking.entities.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>{

}

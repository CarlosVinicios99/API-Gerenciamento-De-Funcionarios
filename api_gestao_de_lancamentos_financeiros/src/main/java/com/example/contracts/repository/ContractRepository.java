package com.example.contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contracts.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{

}

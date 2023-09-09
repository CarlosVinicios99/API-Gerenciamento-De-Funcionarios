package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{

}

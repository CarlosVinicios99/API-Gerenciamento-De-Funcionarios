package com.example.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transactions.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}

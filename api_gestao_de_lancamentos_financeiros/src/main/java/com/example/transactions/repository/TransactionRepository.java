package com.example.transactions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.transactions.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Query("SELECT t FROM Transaction t WHERE t.id_company =:companyId")
	Page<Transaction> findAllTransactionsByCompany(@Param("companyId") Long companyId, Pageable pageable);
	
}

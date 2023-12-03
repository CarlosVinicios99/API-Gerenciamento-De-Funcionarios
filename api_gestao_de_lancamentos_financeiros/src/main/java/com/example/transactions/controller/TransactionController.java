package com.example.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactions.model.Transaction;
import com.example.transactions.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/transactions")
@CrossOrigin
@RestController
@Tag(name = "Transaction", description = "Serviços de Transações")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	
	@Operation(
		summary = "Criar uma nova transação"
	)
	@PostMapping()
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction newTransaction){
		return this.transactionService.createTransaction(newTransaction);
	}
	
	@Operation(
		summary = "Busca uma transação por ID"
	)
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> findTransactionById(@PathVariable Long id){
		return this.transactionService.findTransactionById(id);
	}

	@Operation(
		summary = "Busca todas as transações realizadas por um empresa, de forma paginada"
	)
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Page<Transaction>> findAllTransactionsByCompany(
		@PathVariable Long companyId,
		@RequestParam(defaultValue = "0") int page,  
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return this.transactionService.findAllTransactionsByCompany(companyId, page, limit, direction);
	}
	
	@Operation(
		summary = "Excluí uma transação por ID"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> deleteTransactionById(@PathVariable Long id){
		return this.transactionService.deleteTransactionById(id);
	}
}


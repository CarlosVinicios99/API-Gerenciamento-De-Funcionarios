package com.example.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactions.model.Transaction;
import com.example.transactions.service.TransactionService;

@RequestMapping("/transactions")
@CrossOrigin
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping()
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction newTransaction){
		return this.transactionService.createTransaction(newTransaction);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> findTransactionById(@PathVariable Long id){
		return this.transactionService.findTransactionById(id);
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Page<Transaction>> findAllTransactionsByCompany(
		@PathVariable Long companyId,
		@RequestParam(defaultValue = "0") int page,  
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return this.transactionService.findAllTransactionsByCompany(companyId, page, limit, direction);
	}
	
	@PutMapping()
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction){
		return this.transactionService.updateTransaction(transaction);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> deleteTransactionById(@PathVariable Long id){
		return this.transactionService.deleteTransactionById(id);
	}
}


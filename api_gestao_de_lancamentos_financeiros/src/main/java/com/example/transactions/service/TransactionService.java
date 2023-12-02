package com.example.transactions.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.accounts.service.AccountService;
import com.example.transactions.model.Transaction;
import com.example.transactions.model.enums.TransactionType;
import com.example.transactions.repository.TransactionRepository;

@Service
public class TransactionService {
	
	private Logger logger = Logger.getLogger(TransactionService.class.getName());
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	
	private Boolean confirmTransaction(Transaction transaction) {
		if(transaction.getType() == TransactionType.DEPOSIT) {
			return this.accountService.deposit(transaction.getCompanyId(), transaction.getAmount());
		}
		return this.accountService.withdraw(c, amount);
	}
	
	public ResponseEntity<Transaction> createTransaction(Transaction newTransaction) {
		this.logger.log(Level.INFO, "Criação de transação");
		try {
			if(confirmTransaction(newTransaction)) {
				
			}
			Transaction createdTransaction = transactionRepository.save(newTransaction);
			if(createdTransaction != null) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			this.logger.log(Level.SEVERE, "Erro ao cadastrar uma transação");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao cadastrar uma transação");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Transaction> findTransactionById(Long id){
		this.logger.log(Level.INFO, "Buscando transação por ID");
		try {
			Transaction searchedTransaction  = transactionRepository.findById(id).get();
			if(searchedTransaction != null) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			this.logger.log(Level.WARNING, "Nenhuma transação encontrada!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar transação por ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Page<Transaction>> findAllTransactionsByCompany(Long companyId, int page, int limit, String direction){
		this.logger.log(Level.INFO, "Buscando transações por empresa");
		try {
			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
			Page<Transaction> transactions = transactionRepository.findAllTransactionsByCompany(companyId, pageable);
			return ResponseEntity.status(HttpStatus.OK).body(transactions);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar contratos por empresa");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Transaction> updateTransaction(Transaction transaction){
		this.logger.log(Level.INFO, "Atualizando transação");
		try {
			Transaction updatedTransaction = transactionRepository.findById(transaction.getId()).get();
			if(updatedTransaction == null) {
				logger.log(Level.WARNING, "Nenhuma transação encontrada!");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			if(!transaction.getType().toString().isEmpty()) {
				updatedTransaction.setType(transaction.getType());
			}
			
			if(!transaction.getTransactionDate().toString().isEmpty()) {
				updatedTransaction.setTransactionDate(transaction.getTransactionDate());
			}
						
			if(!transaction.getAmount().toString().isEmpty()) {
				transaction.setAmount(transaction.getAmount());
			}
			
			transactionRepository.save(updatedTransaction);
			return ResponseEntity.status(HttpStatus.OK).build();
					
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao atualizar informações de uma transação", error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Transaction> deleteTransactionById(Long id) {
		try {
			Transaction deletedTransaction = transactionRepository.findById(id).get();
			if(deletedTransaction != null) {
				logger.log(Level.WARNING, "Nenhuma transação encontrada!");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			transactionRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao excluir uma transação por ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
}

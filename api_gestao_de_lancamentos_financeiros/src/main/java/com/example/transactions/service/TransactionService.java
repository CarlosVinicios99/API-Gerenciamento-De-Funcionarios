package com.example.transactions.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.contracts.DTO.UpdateContractDTO;
import com.example.contracts.model.Contract;
import com.example.transactions.model.Transaction;
import com.example.transactions.repository.TransactionRepository;

@Service
public class TransactionService {
	
	private Logger logger = Logger.getLogger(TransactionService.class.getName());
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	public ResponseEntity<Transaction> createTransaction(Transaction newTransaction) {
		this.logger.log(Level.INFO, "Criação de transação");
		try {
			Transaction createdTransaction = transactionRepository.save(newTransaction);
			if(createdTransaction != null) {
				return ResponseEntity.ok().build();
			}
			this.logger.log(Level.SEVERE, "Erro ao cadastrar uma transação");
			return ResponseEntity.unprocessableEntity().build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao cadastrar uma transação");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Transaction> findTransactionById(Long id){
		this.logger.log(Level.INFO, "Buscando transação por ID");
		try {
			Transaction searchedTransaction  = transactionRepository.findById(id).get();
			if(searchedTransaction != null) {
				return ResponseEntity.ok().build();
			}
			this.logger.log(Level.WARNING, "Nenhuma transação encontrada!");
			return ResponseEntity.noContent().build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar transação por ID");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Page<Transaction>> findAllTransactionsByCompany(Long companyId, int page, int limit, String direction){
		this.logger.log(Level.INFO, "Buscando contratos por empresa");
		try {
			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
			Page<Contract> contracts = contractRepository.findAllContractsByCompany(companyId, pageable);
			return ResponseEntity.ok().body(contracts);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar contratos por empresa");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Page<Contract>> findAllContractsByEmployee(Long employeeId, int page, int limit, String direction) {
		this.logger.log(Level.INFO, "Buscando contratos por funcionário");
		try {
			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
			Page<Contract> contracts = contractRepository.findAllContractsByEmployee(employeeId, pageable);
			return ResponseEntity.ok().body(contracts);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar contratos por funcionário");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Contract> updateContract(UpdateContractDTO contract){
		this.logger.log(Level.INFO, "Atualizando contrato");
		try {
			Contract updatedContract = contractRepository.findById(contract.id()).get();
			if(updatedContract == null) {
				logger.log(Level.WARNING, "Nenhum contrato encontrado!");
				return ResponseEntity.noContent().build();
			}
			
			if(!contract.office().isEmpty()) {
				updatedContract.setOffice(contract.office());
			}
			
			if(!contract.level().toString().isEmpty()) {
				updatedContract.setLevel(contract.level());
			}
			
			if(!contract.salaryPerMonth().toString().isEmpty()) {
				updatedContract.setSalaryPerMonth(contract.salaryPerMonth());
			}
			
			if(!contract.durationInMonths().toString().isEmpty()) {
				updatedContract.setDurationInMonths(contract.durationInMonths());
			}
			
			if(!contract.startDate().toString().isEmpty()) {
				updatedContract.setStartDate(contract.startDate());
			}
				
			if(!contract.endDate().toString().isEmpty()) {
				updatedContract.setEndDate(contract.endDate());
			}
				
			if(!contract.status().toString().isEmpty()) {
				updatedContract.setStatus(contract.status());
			}
			
			return ResponseEntity.ok().build();
					
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao atualizar informações de um contrato", error.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Contract> deleteContractById(Long id) {
		try {
			Contract deletedContract = contractRepository.findById(id).get();
			if(deletedContract != null) {
				logger.log(Level.WARNING, "Nenhum contrato encontrado!");
				return ResponseEntity.noContent().build();
			}
			contractRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao excluir um contrato por ID");
			return ResponseEntity.internalServerError().build();
		}
	}
	
}

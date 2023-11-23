package com.example.contracts.service;

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

import com.example.contracts.model.Contract;
import com.example.contracts.repository.ContractRepository;

@Service
public class ContractService {
	
	private Logger logger = Logger.getLogger(ContractService.class.getName());
	
	@Autowired
	private ContractRepository contractRepository;
	
	
	public ResponseEntity<Contract> createContract(Contract newContract) {
		this.logger.log(Level.INFO, "Criação de contrato");
		try {
			Contract createdContract = contractRepository.save(newContract);
			if(createdContract != null) {
				return ResponseEntity.ok().build();
			}
			this.logger.log(Level.SEVERE, "Erro ao cadastrar um contrato");
			return ResponseEntity.unprocessableEntity().build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao cadastrar um contrato");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Contract> findContractById(Long id){
		this.logger.log(Level.INFO, "Buscando contrato por ID");
		try {
			Contract searchedContract  = contractRepository.findById(id).get();
			if(searchedContract != null) {
				return ResponseEntity.ok().build();
			}
			this.logger.log(Level.WARNING, "Nenhum contrato encontrado!");
			return ResponseEntity.noContent().build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar contrato por ID");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Page<Contract>> findAllContractsByCompany(Long companyId, int page, int limit, String direction){
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
	
}

package com.example.contracts.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}

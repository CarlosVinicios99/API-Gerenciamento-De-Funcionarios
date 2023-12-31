package com.example.contracts.controller;

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

import com.example.contracts.DTO.UpdateContractDTO;
import com.example.contracts.model.Contract;
import com.example.contracts.service.ContractService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/contracts")
@CrossOrigin
@RestController
@Tag(name = "Contract", description = "Serviços de Contratos")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	
	@Operation(
		summary = "Cria um novo contrato"
	)
	@PostMapping()
	public ResponseEntity<Contract> createContract(@RequestBody Contract contract){
		return contractService.createContract(contract);
	}
	
	@Operation(
		summary = "Busca um contrato por ID"
	)
	@GetMapping("/{id}")
	public ResponseEntity<Contract> findContractById(@PathVariable Long id){
		return contractService.findContractById(id);
	}
	
	@Operation(
		summary = "Busca todos os contratos por empresa de forma paginada"
	)
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Page<Contract>> findAllContractsByCompany(
		@PathVariable Long companyId,
		@RequestParam(defaultValue = "0") int page,  
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return contractService.findAllContractsByCompany(companyId, page, limit, direction);
	}
	
	@Operation(
		summary = "Busca todos os contratos por funcionário, de forma paginada"
	)
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Page<Contract>> findAllContractsByEmployee(
		@PathVariable Long employeeId,
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return contractService.findAllContractsByEmployee(employeeId, page, limit, direction);
	}
	
	@Operation(
		summary = "Atualiza as informações de um contrato"
	)
	@PutMapping()
	public ResponseEntity<Contract> updateCompany(@RequestBody UpdateContractDTO contract){
		return contractService.updateContract(contract);
	}
	
	@Operation(
		summary = "Excluí um contrato por ID"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Contract> deleteCompanybyId(@PathVariable Long id){
		return contractService.deleteContractById(id);
	}
	
}

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

@RequestMapping("/contracts")
@CrossOrigin
@RestController
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@PostMapping()
	public ResponseEntity<Contract> createContract(@RequestBody Contract contract){
		return contractService.createContract(contract);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contract> findContractById(@PathVariable Long id){
		return contractService.findContractById(id);
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Page<Contract>> findAllContractsByCompany(
		@PathVariable Long companyId,
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return contractService.findAllContractsByCompany(companyId, page, limit, direction);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Page<Contract>> findAllContractsByEmployee(
		@PathVariable Long employeeId,
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return contractService.findAllContractsByEmployee(employeeId, page, limit, direction);
	}
	
	@PutMapping()
	public ResponseEntity<Contract> updateCompany(@RequestBody UpdateContractDTO contract){
		return contractService.updateContract(contract);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Contract> deleteCompanybyId(@PathVariable Long id){
		return contractService.deleteContractById(id);
	}
	
}

package com.example.companies.controller;

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

import com.example.companies.DTO.UpdateCompanyDTO;
import com.example.companies.model.Company;
import com.example.companies.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/companies")
@CrossOrigin
@RestController
@Tag(name = "Company", description = "Serviços de Empresas")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	
	@Operation(
		summary = "Cria uma nova empresa"
	)
	@PostMapping()
	public ResponseEntity<Company> createCompany(@RequestBody Company company){
		return companyService.createCompany(company);
	}
	
	@Operation(
		summary = "Busca uma empresa por ID"
	)
	@GetMapping("/{id}")
	public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
		return companyService.findCompanyById(id);
	}
	
	@Operation(
		summary = "Busca todas as empresas, de forma paginada"
	)
	@GetMapping()
	public ResponseEntity<Page<Company>> findAllCompanies(
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return companyService.findAllCompanies(page, limit, direction);
	}
	
	@Operation(
		summary = "Atualiza as informações de uma empresa"
	)
	@PutMapping()
	public ResponseEntity<Company> updateCompany(@RequestBody UpdateCompanyDTO company){
		return companyService.updateCompany(company);
	}
	
	@Operation(
		summary = "Excluí uma empresa por ID"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Company> deleteCompanybyId(@PathVariable Long id){
		return companyService.deleteCompanyById(id);
	}
	
}

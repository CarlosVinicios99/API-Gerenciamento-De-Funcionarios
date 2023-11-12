package com.example.companies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.companies.DTO.UpdateCompanyDTO;
import com.example.companies.model.Company;
import com.example.companies.service.CompanyService;

@RequestMapping("/companies")
@CrossOrigin
@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	
	public ResponseEntity<Company> createCompany(@RequestBody Company company){
		return companyService.createCompany(company);
	}
	
	public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
		return companyService.findCompanyById(id);
	}
	
	public ResponseEntity<Page<Company>> findAllCompanies(
		@RequestParam int page, 
		@RequestParam int limit, 
		@RequestParam String direction
	){
		return companyService.findAllCompanies(page, limit, direction);
	}
	
	public ResponseEntity<Company> updateCompany(@RequestBody UpdateCompanyDTO company){
		return companyService.updateCompany(company);
	}
	
	public ResponseEntity<Company> deleteCompanybyId(@PathVariable Long id){
		return companyService.deleteCompanyById(id);
	}
	
}

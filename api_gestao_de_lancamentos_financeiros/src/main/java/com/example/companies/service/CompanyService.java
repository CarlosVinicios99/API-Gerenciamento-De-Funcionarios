package com.example.companies.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.companies.model.Company;
import com.example.companies.repository.CompanyRepository;


@Service
public class CompanyService {
	
	private Logger logger = Logger.getLogger(CompanyService.class.getName());
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	public ResponseEntity<Company> createCompany(Company newCompany){
		logger.log(Level.INFO, "Cadastrando uma empresa");
		try {
			Company createdCompany = companyRepository.save(newCompany);
			if(createdCompany != null) {
				return ResponseEntity.ok().build();
			}
			logger.log(Level.SEVERE, "Erro ao cadastrar uma empresa, informações de cadastro incorretas!");
			return ResponseEntity.unprocessableEntity().build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao cadastrar uma empresa. ", error.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
}

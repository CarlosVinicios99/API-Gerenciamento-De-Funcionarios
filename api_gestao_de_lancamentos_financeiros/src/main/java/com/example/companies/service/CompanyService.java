package com.example.companies.service;

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

import com.example.accounts.model.Account;
import com.example.accounts.service.AccountService;
import com.example.companies.DTO.UpdateCompanyDTO;
import com.example.companies.model.Company;
import com.example.companies.repository.CompanyRepository;


@Service
public class CompanyService {
	
	private Logger logger = Logger.getLogger(CompanyService.class.getName());
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private AccountService accountService;
	
	
	public ResponseEntity<Company> createCompany(Company newCompany){
		logger.log(Level.INFO, "Cadastrando uma empresa");
		try {
			Account newAccount = accountService.createAccount();
			if(newAccount == null) {
				logger.log(Level.SEVERE, "Erro ao cadastrar uma empresa");
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
			}
			
			newCompany.setAccount(newAccount);
			Company createdCompany = companyRepository.save(newCompany);
			
			if(createdCompany != null) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			
			logger.log(Level.SEVERE, "Erro ao cadastrar uma empresa, informações de cadastro incorretas!");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao cadastrar uma empresa ", error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Company> findCompanyById(Long id){
		logger.log(Level.INFO, "Buscando uma empresa por ID");
		try {
			Company searchedCompany = companyRepository.findById(id).get();
			if(searchedCompany != null) {
				return ResponseEntity.status(HttpStatus.OK).body(searchedCompany);
			}
			logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao buscar uma empresa por ID ", error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Page<Company>> findAllCompanies(int page, int limit, String direction){
		logger.log(Level.INFO, "Buscando todas as empresas cadastradas!");
		try {
			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
			Page<Company> companies = companyRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(companies);
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao buscar uma empresa por ID ", error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Company> updateCompany(UpdateCompanyDTO company){
		logger.log(Level.INFO, "Atualizando as informações de uma empresa!");
		try {
			Company updatedCompany = companyRepository.findById(company.id()).get();
			if(updatedCompany == null) {
				logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			if(!company.email().isEmpty()) {
				updatedCompany.setEmail(company.email());
			}
			
			if(!company.name().isEmpty()) {
				updatedCompany.setName(company.name());
			}
			
			if(!company.cnpj().isEmpty()) {
				updatedCompany.setCnpj(company.cnpj());
			}
			
			if(!company.password().isEmpty()) {
				updatedCompany.setPassword(company.password());
			}
			
			companyRepository.save(updatedCompany);
			return ResponseEntity.status(HttpStatus.OK).build();
					
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao atualizar informações de uma empresa ", error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Company> deleteCompanyById(Long id){
		logger.log(Level.INFO, "Excluindo uma empresa por ID");
		try {
			Company deletedCompany = companyRepository.findById(id).get();
			if(deletedCompany == null) {
				logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			companyRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao excluir uma empresa por ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
}

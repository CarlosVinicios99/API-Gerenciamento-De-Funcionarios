package com.example.companies.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.companies.DTO.UpdateCompanyDTO;
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
			logger.log(Level.SEVERE, "Erro ao cadastrar uma empresa ", error.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Company> findById(Long id){
		logger.log(Level.INFO, "Buscando uma empresa por ID");
		try {
			Company searchedCompany = companyRepository.findById(id).get();
			if(searchedCompany != null) {
				return ResponseEntity.ok().body(searchedCompany);
			}
			logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
			return ResponseEntity.noContent().build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao buscar uma empresa por ID ", error.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	//adicionar busca por varias empresas com paginação
	
	public ResponseEntity<Company> updateCompany(UpdateCompanyDTO company){
		logger.log(Level.INFO, "Atualizando as informações de uma empresa");
		try {
			Company updatedCompany = companyRepository.findById(company.id()).get();
			if(updatedCompany == null) {
				logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
				return ResponseEntity.noContent().build();
			}
			
			if(!company.email().isEmpty())
				updatedCompany.setEmail(company.email());
			
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
			return ResponseEntity.ok().build();
					
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao atualizar informações de uma empresa ", error.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	public ResponseEntity<Company> deleteCompanyById(Long id){
		logger.log(Level.INFO, "Excluindo uma empresa por ID");
		try {
			Company deletedCompany = companyRepository.findById(id).get();
			if(deletedCompany != null) {
				logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
				return ResponseEntity.noContent().build();
			}
			companyRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao excluir uma empresa por ID");
			return ResponseEntity.internalServerError().build();
		}
	}
	
}

package com.example.employees.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.companies.service.CompanyService;
import com.example.employees.model.Employee;
import com.example.employees.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private Logger logger = Logger.getLogger(CompanyService.class.getName());
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public ResponseEntity<Employee> createEmployee(Employee newEmployee){
		this.logger.log(Level.INFO, "Cadastrando um funcionário");
		try {
			Employee createdEmployee = employeeRepository.save(newEmployee);
			if(createdEmployee != null) {
				return ResponseEntity.ok().build();
			}
			this.logger.log(Level.SEVERE, "Erro ao cadastrar um funcionário");
			return ResponseEntity.unprocessableEntity().build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao cadastrar funcionário");
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Employee> findByEmployeeById(Long id){
		this.logger.log(Level.INFO, "Buscar um funcionário por ID");
		try {
			Employee searchedEmployee = employeeRepository.findById(id).get();
			if(searchedEmployee != null) {
				return ResponseEntity.ok().body(searchedEmployee);
			}
			logger.log(Level.WARNING, "Nenhum funcionário encontrado!");
			return ResponseEntity.noContent().build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar funcionário por ID");
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
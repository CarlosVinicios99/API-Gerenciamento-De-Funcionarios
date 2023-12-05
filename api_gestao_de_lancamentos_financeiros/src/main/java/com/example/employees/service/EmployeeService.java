package com.example.employees.service;

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

import com.example.companies.service.CompanyService;
import com.example.employees.DTO.UpdateEmployeeDTO;
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
			System.out.println(newEmployee.getAgency());
			Employee createdEmployee = employeeRepository.save(newEmployee);
			if(createdEmployee != null) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			this.logger.log(Level.SEVERE, "Erro ao cadastrar um funcionário");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao cadastrar funcionário");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Employee> findByEmployeeById(Long id){
		this.logger.log(Level.INFO, "Buscando um funcionário por ID");
		try {
			Employee searchedEmployee = employeeRepository.findById(id).get();
			if(searchedEmployee != null) {
				return ResponseEntity.status(HttpStatus.OK).body(searchedEmployee);
			}
			logger.log(Level.WARNING, "Nenhum funcionário encontrado!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar funcionário por ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Page<Employee>> findAllEmployeesByCompany(Long companyId, int page, int limit, String direction){
		this.logger.log(Level.INFO, "Buscar funcionários por empresa");
		try {
			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
			Page<Employee> employees = employeeRepository.findAllEmployeesByCompanyId(companyId, pageable);
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Erro ao buscar funcionários por empresa");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Employee> updateEmployee(UpdateEmployeeDTO employee){
		logger.log(Level.INFO, "Atualizando as informações de um funcionário!");
		try {
			Employee updatedEmployee = employeeRepository.findById(employee.id()).get();
			if(updatedEmployee == null) {
				logger.log(Level.WARNING, "Nenhuma empresa encontrada!");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			if(!employee.email().isEmpty()) {
				updatedEmployee.setEmail(employee.email());
			}
			
			if(!employee.fullName().isEmpty()) {
				updatedEmployee.setFullName(employee.fullName());
			}
			
			if(!employee.agency().isEmpty()) {
				updatedEmployee.setAgency(employee.agency());
			}
			
			if(!employee.checkingAccount().isEmpty()) {
				updatedEmployee.setCheckingAccount(employee.checkingAccount());
			}
			
			employeeRepository.save(updatedEmployee);
			return ResponseEntity.status(HttpStatus.OK).build();
					
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao atualizar informações de um funcionário", error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Employee> deleteEmployeeById(Long id){
		logger.log(Level.INFO, "Excluindo um funcionário por ID");
		try {
			Employee deletedEmployee = employeeRepository.findById(id).get();
			if(deletedEmployee == null) {
				logger.log(Level.WARNING, "Nenhum funcionário encontrado!");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			employeeRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao excluir uma empresa por ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
		
}



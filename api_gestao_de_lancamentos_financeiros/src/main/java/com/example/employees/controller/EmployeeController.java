package com.example.employees.controller;

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

import com.example.employees.DTO.UpdateEmployeeDTO;
import com.example.employees.model.Employee;
import com.example.employees.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/employees")
@RestController
@CrossOrigin
@Tag(name = "Employee", description = "Serviços de Funcionários")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@Operation(
		summary = "Cadastra um novo funcionário"
	)
	@PostMapping()
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		return employeeService.createEmployee(employee);
	}
	
	@Operation(
		summary = "Busca um funcionário por ID"
	)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id){
		return employeeService.findByEmployeeById(id);
	}
	
	@Operation(
		summary = "Busca todos os funcionários por empresa, de forma paginada"
	)
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Page<Employee>> findEmployeesByCompany(
		@PathVariable Long companyId,
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return employeeService.findAllEmployeesByCompany(companyId, page, limit, direction);
	}
	
	@Operation(
		summary = "Atualiza as informações de um funcionário"
	)
	@PutMapping()
	public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeDTO employee){
		return employeeService.updateEmployee(employee);
	}
	
	@Operation(
		summary = "Excluí um funcionário por ID"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id){
		return employeeService.deleteEmployeeById(id);
	}
	
}

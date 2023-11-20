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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employees.DTO.UpdateEmployeeDTO;
import com.example.employees.model.Employee;
import com.example.employees.service.EmployeeService;

@RequestMapping("/employees")
@RestController
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@PostMapping()
	public ResponseEntity<Employee> createEmployee(Employee employee){
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployeeById(Long id){
		return employeeService.findByEmployeeById(id);
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<Page<Employee>> findEmployeesByCompany(
		@PathVariable Long companyId,
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int limit, 
		@RequestParam(defaultValue = "asc") String direction
	){
		return employeeService.findAllEmployeesByCompany(companyId, page, limit, direction);
	}
	
	@PutMapping()
	public ResponseEntity<Employee> updateEmployee(UpdateEmployeeDTO employee){
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping()
	public ResponseEntity<Employee> deleteEmployeeById(Long id){
		return employeeService.deleteEmployeeById(id);
	}
	
}

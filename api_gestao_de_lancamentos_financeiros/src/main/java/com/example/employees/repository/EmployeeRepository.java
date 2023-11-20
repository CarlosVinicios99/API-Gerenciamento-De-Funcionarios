package com.example.employees.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employees.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query("SELECT e FROM Employee e WHERE e.id_company =:companyId")
	Page<Employee> findAllEmployeesByCompany(@Param("companyId") Long companyId, Pageable pageable);
	
}

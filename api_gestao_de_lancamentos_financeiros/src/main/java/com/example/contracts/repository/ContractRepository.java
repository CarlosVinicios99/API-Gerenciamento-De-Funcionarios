package com.example.contracts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.contracts.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{
	
	@Query("SELECT c FROM Employee c WHERE c.id_company =:companyId")
	Page<Contract> findAllContractsByCompany(@Param("companyId") Long companyId, Pageable pageable);
	
	@Query("SELECT c FROM Employee c WHERE c.id_employee =:employeeId")
	Page<Contract> findAllContractsByEmployee(@Param("employeeId") Long employeeId, Pageable pageable);
	
}

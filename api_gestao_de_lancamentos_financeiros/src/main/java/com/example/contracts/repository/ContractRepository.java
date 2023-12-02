package com.example.contracts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contracts.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{
	
	Page<Contract> findAllContractsByCompanyId(Long companyId, Pageable pageable);
	Page<Contract> findAllContractsByEmployeeId(Long employeeId, Pageable pageable);
	
}

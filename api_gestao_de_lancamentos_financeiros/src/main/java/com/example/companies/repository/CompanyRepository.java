package com.example.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.companies.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}

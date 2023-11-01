package com.example.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accounts.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}

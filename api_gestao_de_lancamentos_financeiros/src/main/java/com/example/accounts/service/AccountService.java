package com.example.accounts.service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accounts.model.Account;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.utils.AccountGenerator;

@Service
public class AccountService {
	
	private Logger logger = Logger.getLogger(AccountService.class.getName());
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public Account createAccount() {
		Account account = new Account();
		account.setAccountNumber(AccountGenerator.generateAccountNumber());
		account.setBalance(0.0);
		account.setCreationDate(new Date());
		
		this.logger.log(Level.INFO, "Criando uma conta");
		
		try {
			return accountRepository.save(account);
		}
		catch(Exception error) {
			logger.log(Level.SEVERE, "Erro ao criar uma conta", error.getMessage());
			return null;
		}
	}
	
	public Boolean deposit(Long id, Double amount) {
		try {
			this.logger.log(Level.INFO, "Realizando depósito");
			Account account = accountRepository.findById(id).get();
			account.setBalance(account.getBalance() + amount);
			accountRepository.save(account);
			return true;
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "Não foi possível realizar depósito", error.getMessage());
			return false;
		}
	}
	
	public Boolean withdraw(Long id, Double amount) {
		try {
			this.logger.log(Level.INFO, "Realizando saque");
			Account account = accountRepository.findById(id).get();
			
			if(account.getBalance() < amount) {
				this.logger.log(Level.INFO, "Saldo insuficiente");
				return false;
			}
			
			account.setBalance(account.getBalance() - amount);
			accountRepository.save(account);	
			return true;
		}
		catch(Exception error) {
			this.logger.log(Level.INFO, "Não foi possível realizar saque");
			return false;
		}
	}
} 

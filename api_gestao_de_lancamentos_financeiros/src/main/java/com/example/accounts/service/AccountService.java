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
	
}

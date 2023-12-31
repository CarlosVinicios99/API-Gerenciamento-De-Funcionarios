package com.example.accounts.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column()
	private Double balance;
	
	
	public Account() {
		
	}

	public Account(Long id, String accountNumber, Date creationDate, Double balance) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.creationDate = creationDate;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance, creationDate, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(balance, other.balance)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id);
	}
	
}

package com.example.companies.model;

import java.io.Serializable;
import java.util.Objects;

import com.example.accounts.model.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_companies")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column()
	private String name;
	
	@Column()
	private String email;
	
	@Column()
	private String password;
	
	@Column()
	private String cnpj;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Account account;
	
	public Company() {
		
	}
	
	public Company(Long id, String name, String email, String password, String cnpj, Account account) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cnpj = cnpj;
		this.account = account;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	@Override
	public int hashCode() {
		return Objects.hash(account, cnpj, email, id, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(account, other.account) && Objects.equals(cnpj, other.cnpj)
			&& Objects.equals(email, other.email)&& Objects.equals(id, other.id)
			&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}
	
}

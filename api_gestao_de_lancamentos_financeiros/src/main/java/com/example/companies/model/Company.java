package com.example.companies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.accounts.model.Account;
import com.example.contracts.model.Contract;
import com.example.employees.model.Employee;
import com.example.transactions.model.Transaction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TABLE_COMPANIES")
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
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_company")
	private List<Employee> employees = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_company")
	private List<Contract> contracts = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_company")
	private List<Transaction> transactions = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Account account;
	
	
	public Company() {
	
	}
	
	public Company(Long id, String name, String email, String password, String cnpj, List<Employee> employees,
			List<Contract> contracts, List<Transaction> transactions, Account account) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cnpj = cnpj;
		this.employees = employees;
		this.contracts = contracts;
		this.transactions = transactions;
		this.account = account;
	}

	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	@Override
	public int hashCode() {
		return Objects.hash(account, cnpj, contracts, email, employees, id, name, password, transactions);
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
				&& Objects.equals(contracts, other.contracts) && Objects.equals(email, other.email)
				&& Objects.equals(employees, other.employees) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(transactions, other.transactions);
	}
	

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
	}
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(Contract contract) {
		this.contracts.remove(contract);
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public void removeTransaction(Transaction transaction) {
		this.transactions.remove(transaction);
	}
	
}

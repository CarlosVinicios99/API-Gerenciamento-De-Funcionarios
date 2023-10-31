package com.example.companies;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.example.accounts.Account;
import com.example.employees.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@Column(nullable = false, length = 60)
	private String name;
	
	@Column(nullable = false, unique = true, length = 80)
	private String email;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, unique = true, length = 20)
	private String cnpj;
	
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Employee> employees;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Account account;
	
	public Company() {
		
	}

	public Company(Long id, String name, String email, String password, String cnpj, List<Employee> employees, Account account) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cnpj = cnpj;
		this.employees = employees;
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
		return Objects.hash(account, cnpj, email, employees, id, name, password);
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
			&& Objects.equals(email, other.email) && Objects.equals(employees, other.employees)
			&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
			&& Objects.equals(password, other.password);
	}
	
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
	}
	
}

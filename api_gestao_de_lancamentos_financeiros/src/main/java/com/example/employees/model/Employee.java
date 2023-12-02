package com.example.employees.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column()
	private String email;
	
	@Column()
	private String agency;
	
	@Column(name = "checking_account")
	private String checkingAccount;
	
	@Column(name = "id_company")
	private Long companyId;
	
	public Employee() {
		
	}

	public Employee(Long id, String fullName, String email, String agency, String checkingAccount, Long companyId) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.agency = agency;
		this.checkingAccount = checkingAccount;
		this.companyId = companyId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(String checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	
	public Long companyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agency, checkingAccount, email, fullName, id, companyId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(agency, other.agency) && Objects.equals(checkingAccount, other.checkingAccount)
			&& Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
			&& Objects.equals(id, other.id) && Objects.equals(companyId, other.companyId);
	}
	
	
}

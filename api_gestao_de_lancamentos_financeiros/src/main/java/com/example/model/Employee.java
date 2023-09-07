package com.example.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TABLE_EMPLOYEES")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name", nullable = false, length = 100)
	private String fullName;
	
	@Column(nullable = false, unique = true, length = 80)
	private String email;
	
	@Column(nullable = false, length = 8)
	private String agency;
	
	@Column(name = "checking_account", nullable = false, unique = true, length = 15)
	private String checkingAccount;
	
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Contract> contracts;
	
	public Employee() {
		
	}

	public Employee(Long id, String fullName, String email, String agency, String checkingAccount, List<Contract> contracts) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.agency = agency;
		this.checkingAccount = checkingAccount;
		this.contracts = contracts;
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
	
	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(agency, checkingAccount, email, fullName, id);
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
				&& Objects.equals(id, other.id);
	}
	
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(Contract contract) {
		this.contracts.remove(contract);
	}
	
}

package com.example.transactions.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.example.transactions.model.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_transactions")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column()
	private Date transactionDate;
	
	@Column()
	private Double amount;
	
	@Column(name = "id_company")
	private Long companyId;
	
	
	public Transaction() {
		
	}
	
	public Transaction(Long id, TransactionType type, Date transactionDate, Double amount, Long companyId) {
		this.id = id;
		this.type = type;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.companyId = companyId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, companyId, id, transactionDate, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(companyId, other.companyId)
			&& Objects.equals(id, other.id) && Objects.equals(transactionDate, other.transactionDate)
			&& type == other.type;
	}
	
}

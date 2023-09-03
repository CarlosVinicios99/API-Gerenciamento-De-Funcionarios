package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.example.model.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TABLE_TRANSACTIONS")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private TransactionType type;
	
	private Date transactionDate;
	
	private Double amount;
	
	
	public Transaction() {
		
	}

	
	public Transaction(Long id, TransactionType type, Date transactionDate, Double amount) {
		this.id = id;
		this.type = type;
		this.transactionDate = transactionDate;
		this.amount = amount;
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


	@Override
	public int hashCode() {
		return Objects.hash(amount, id, transactionDate, type);
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
		return Objects.equals(amount, other.amount) && Objects.equals(id, other.id)
			&& Objects.equals(transactionDate, other.transactionDate) && type == other.type;
	}	
	
}

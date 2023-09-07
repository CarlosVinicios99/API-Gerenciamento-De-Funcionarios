package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.example.model.enums.Level;
import com.example.model.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TABLE_CONTRACTS")
public class Contract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 60)
	private String office;
	
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@Column(nullable = false)
	private Double salaryPerMonth;
	
	@Column(nullable = false)
	private Integer durationInMonths;
	
	@Column(nullable = false)
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(nullable = true)
	private Date endDate;
	
	
	public Contract() {
		
	}


	public Contract(Long id, String office, Level level, Double salaryPerMonth, Integer durationInMonths,
		Date startDate, Status status, Date endDate) {
			this.id = id;
			this.office = office;
			this.level = level;
			this.salaryPerMonth = salaryPerMonth;
			this.durationInMonths = durationInMonths;
			this.startDate = startDate;
			this.status = status;
			this.endDate = endDate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Double getSalaryPerMonth() {
		return salaryPerMonth;
	}

	public void setSalaryPerMonth(Double salaryPerMonth) {
		this.salaryPerMonth = salaryPerMonth;
	}

	public Integer getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(Integer durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(durationInMonths, endDate, id, level, office, salaryPerMonth, startDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		return Objects.equals(durationInMonths, other.durationInMonths) && Objects.equals(endDate, other.endDate)
			&& Objects.equals(id, other.id) && level == other.level && Objects.equals(office, other.office)
			&& Objects.equals(salaryPerMonth, other.salaryPerMonth) && Objects.equals(startDate, other.startDate)
			&& status == other.status;
	}
	
}

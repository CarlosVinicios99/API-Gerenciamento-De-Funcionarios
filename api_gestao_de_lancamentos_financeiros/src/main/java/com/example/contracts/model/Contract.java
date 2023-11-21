package com.example.contracts.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.example.contracts.model.enums.Level;
import com.example.contracts.model.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_contracts")
public class Contract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column()
	private String office;
	
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@Column(name = "salary_per_month")
	private Double salaryPerMonth;
	
	@Column(name = "duration_in_months")
	private Integer durationInMonths;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "id_company")
	private Long companyId;
	
	@Column(name = "id_employee")
	private Long employeeId;
	
	public Contract() {
		
	}


	public Contract(Long id, String office, Level level, Double salaryPerMonth, Integer durationInMonths,
		Date startDate, Status status, Date endDate, Long companyId, Long employeeId) {
			this.id = id;
			this.office = office;
			this.level = level;
			this.salaryPerMonth = salaryPerMonth;
			this.durationInMonths = durationInMonths;
			this.startDate = startDate;
			this.status = status;
			this.endDate = endDate;
			this.companyId = companyId;
			this.employeeId = employeeId;
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
	
	public Long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}


	public Long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(companyId, durationInMonths, employeeId, endDate, id, level, office, salaryPerMonth,
				startDate, status);
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
		return Objects.equals(companyId, other.companyId) && Objects.equals(durationInMonths, other.durationInMonths)
			&& Objects.equals(employeeId, other.employeeId) && Objects.equals(endDate, other.endDate)
			&& Objects.equals(id, other.id) && level == other.level && Objects.equals(office, other.office)
			&& Objects.equals(salaryPerMonth, other.salaryPerMonth) && Objects.equals(startDate, other.startDate)
			&& status == other.status;
	}
	
}

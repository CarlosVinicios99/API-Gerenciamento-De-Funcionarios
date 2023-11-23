package com.example.contracts.DTO;

import com.example.contracts.model.enums.Level;
import com.example.contracts.model.enums.Status;
import java.util.Date;

public record UpdateContractDTO(
		Long id, String office, Level level, Double salaryPerMonth,
		Integer durationInMonths, Date startDate, Date endDate, Status status
){

}


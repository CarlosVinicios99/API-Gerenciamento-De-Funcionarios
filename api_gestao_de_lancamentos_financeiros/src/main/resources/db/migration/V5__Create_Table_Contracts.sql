CREATE TABLE table_contracts(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	office VARCHAR(60) NOT NULL,
	level ENUM('INTERN', 'JUNIOR', 'MID_LEVEL', 'SENIOR') NOT NULL,
	salary_per_month DECIMAL(8, 2) NOT NULL,
	duration_in_months INTEGER NOT NULL,
	start_date DATE NOT NULL,
	status ENUM('CURRENT', 'CONCLUDED', 'TERMINATED_BY_PROMOTION', 'TERMINATED_BY_DEMISSION') NOT NULL,
	end_date DATE NOT NULL,
	id_company INT NOT NULL,
	id_employee INT NOT NULL,
	FOREIGN KEY (id_company) REFERENCES table_companies(id),
	FOREIGN KEY (id_employee) REFERENCES table_employees(id)
);
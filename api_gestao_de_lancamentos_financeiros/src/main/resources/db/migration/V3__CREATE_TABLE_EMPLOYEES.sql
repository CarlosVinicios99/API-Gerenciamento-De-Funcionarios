CREATE TABLE table_employees
(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	full_name VARCHAR(100) NOT NULL,
	email VARCHAR(80) NOT NULL UNIQUE,
	agency VARCHAR(8) NOT NULL,
	checking_account VARCHAR(15) NOT NULL UNIQUE,
	id_company INTEGER NOT NULL,
	FOREIGN KEY (id_company) REFERENCES table_companies(id)
);



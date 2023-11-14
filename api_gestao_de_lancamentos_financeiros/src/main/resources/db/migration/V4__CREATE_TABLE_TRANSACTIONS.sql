CREATE TABLE table_transactions
(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	transaction_date DATE NOT NULL,
	transaction_type ENUM('DEPOSIT', 'PAYMENT') NOT NULL,
	amount DECIMAL(8,2) NOT NULL,
	id_company INT NOT NULL,
	FOREIGN KEY (id_company) REFERENCES table_companies(id)
);

CREATE TABLE TABLE_ACCOUNTS 
(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	creation_date DATE NOT NULL,
	account_number VARCHAR(20) NOT NULL,
	balance DECIMAL(8, 2) NOT NULL
) 
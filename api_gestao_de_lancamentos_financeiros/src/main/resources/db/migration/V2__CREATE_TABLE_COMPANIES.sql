CREATE TABLE TABLE_COMPANIES 
(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	email VARCHAR(80) NOT NULL,
	password VARCHAR(100) NOT NULL,
	cnpj VARCHAR(20) NOT NULL UNIQUE
)
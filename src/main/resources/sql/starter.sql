CREATE DATABASE restDemo;

USE restDemo;

CREATE TABLE Customers (
	id BIGINT(20) NOT NULL AUTO_INCREMENT Primary Key,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    email VARCHAR(35) NOT NULL,
    password VARCHAR(80) NOT NULL
    );

CREATE TABLE Debts (
	id BIGINT(20) NOT NULL AUTO_INCREMENT Primary Key,
	amount DOUBLE NOT NULL,
	currency VARCHAR(4) NOT NULL,
    due_date DATE NOT NULL,
    customer_id BIGINT(20) NOT NULL
    );

INSERT INTO Customers (First_Name, Last_Name, country, email, password)
VALUES ('Tom', 'Tikver', 'USA', 'tom.tikver@gmail.com', 'tikver-93d'),
		('Alex', 'Moore', 'Germany', 'alex.moore@gmail.com', 'moorex2-8d');
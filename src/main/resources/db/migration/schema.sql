CREATE SCHEMA IF NOT EXISTS Seraphine;
USE seraphine;

DROP TABLE User;
DROP TABLE Doctor;
DROP TABLE Appointment;
DROP TABLE Admin;

CREATE TABLE User (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR (100),
    email VARCHAR(30),
    user_name VARCHAR (30),
    passwords VARCHAR (1000),
    date_of_birth VARCHAR(12),
    insurance_name VARCHAR(30),
    insurance_type VARCHAR(30),
    health_status VARCHAR(100),
    health_issues VARCHAR (1000),
    primary key(id)
);

CREATE TABLE Doctor (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    gender VARCHAR(1),
    specialization VARCHAR(20),
    primary key(id)
);

CREATE TABLE Appointment (
    id INT NOT NULL,
    doctor_id INT NOT NULL,
    datetime DATETIME NOT NULL,
    appointment_type VARCHAR(50),
    appointment_description VARCHAR(1000),
    primary key(id),
    foreign key(doctor_id) references Doctor(id) ON DELETE CASCADE
);

CREATE TABLE Admin (
	user_id INT NOT NULL,
    user_name VARCHAR(100),
    passwords VARCHAR(100),
    primary key(user_id),
    foreign key(user_id) references User(id) ON DELETE CASCADE
)

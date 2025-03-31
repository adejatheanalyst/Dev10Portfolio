DROP DATABASE IF EXISTS instrument_inventory;
CREATE DATABASE instrument_inventory;
USE instrument_inventory;

CREATE TABLE instruments (
	instrument_id INT PRIMARY KEY AUTO_INCREMENT,
    serial_number VARCHAR(255) UNIQUE NOT NULL,
    `type` TEXT NOT NULL,
	cost INT NOT NULL,
    student TEXT NOT NULL,
    needs_repair BOOLEAN NOT NULL
);

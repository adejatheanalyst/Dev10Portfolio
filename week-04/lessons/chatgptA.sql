-- DDL FILE
drop database if exists chatgpta;


create database chatgpta;
use chatgpta;


-- MySQL DDL and DML File: Database Schema Definition

-- Create Theaters Table
CREATE TABLE Theaters (
    theater_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(255)
);

-- Create Customers Table
CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(20),
    address TEXT
);

-- Create Shows Table
CREATE TABLE Shows (
    show_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    theater_id INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES Theaters(theater_id) ON DELETE CASCADE
);

-- Create Tickets Table
CREATE TABLE Tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    show_id INT NOT NULL,
    seat VARCHAR(10) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES Shows(show_id) ON DELETE CASCADE
);

-- Create Reservations Table
CREATE TABLE Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    show_id INT NOT NULL,
    ticket_id INT NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES Shows(show_id) ON DELETE CASCADE,
    FOREIGN KEY (ticket_id) REFERENCES Tickets(ticket_id) ON DELETE CASCADE
);

-- Create ReservationDetails Table
CREATE TABLE ReservationDetails (
    reservation_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    phone VARCHAR(20),
    address TEXT,
    show_title VARCHAR(255),
    show_date DATE,
    seat VARCHAR(10),
    price DECIMAL(10,2),
    theater_name VARCHAR(255),
    theater_address TEXT,
    reservation_date TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id) ON DELETE CASCADE
);

-- Insert Theaters
INSERT INTO Theaters (name, address, phone, email) VALUES
('Horizon', '70 Meadow Valley Parkway, Saint Paul, MN 55103', '(651) 555-5555', 'horizon@rcttc.com'),
('Little Fitz', '10 E Exchange St, St Paul, MN 55101', '(651) 555-5555', 'little.fitz@rcttc.com'),
('Grand Majestic', '500 Grand Ave, Saint Paul, MN 55102', '(651) 555-1234', 'grand.majestic@rcttc.com');

-- Insert Customers
INSERT INTO Customers (first_name, last_name, email, phone, address) VALUES
('Husain', 'Scheu', 'hscheujs@ovh.net', NULL, '1 Oneill Terrace'),
('Fax', 'Geraudel', 'fgeraudel2n@ted.com', '342-454-2119', '726 Fairfield Alley'),
('Melamie', 'Feighry', 'mfeighrygk@cdc.gov', '1-801-EAT-CAKE', '6 Anzinger Park');

-- Insert Shows
INSERT INTO Shows (title, theater_id, date) VALUES
('Caddyshack', 1, '2024-01-05'),
('Dance Dance Vertical', 2, '2024-01-05'),
('The Grand Performance', 3, '2024-02-10');

-- Insert Tickets
INSERT INTO Tickets (customer_id, show_id, seat, price) VALUES
(1, 1, 'A4', 22.25),
(2, 2, 'C3', 18.95),
(3, 3, 'E4', 22.50);

-- Insert Reservations
INSERT INTO Reservations (customer_id, show_id, ticket_id) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

-- Populate ReservationDetails Table
INSERT INTO ReservationDetails
SELECT 
    r.reservation_id,
    c.first_name,
    c.last_name,
    c.email,
    c.phone,
    c.address,
    s.title AS show_title,
    s.date AS show_date,
    t.seat,
    t.price,
    th.name AS theater_name,
    th.address AS theater_address,
    r.reservation_date
FROM Reservations r
JOIN Customers c ON r.customer_id = c.customer_id
JOIN Shows s ON r.show_id = s.show_id
JOIN Tickets t ON r.ticket_id = t.ticket_id
JOIN Theaters th ON s.theater_id = th.theater_id;

-- Update Ticket Price for Caddyshack
UPDATE Tickets SET price = 22.25 WHERE show_id = (SELECT show_id FROM Shows WHERE title = 'Caddyshack');

-- Update Husain's Seat to A4
UPDATE Tickets SET seat = 'A4' WHERE customer_id = (SELECT customer_id FROM Customers WHERE first_name = 'Husain' AND last_name = 'Scheu');

-- Update Melamie's Phone Number
UPDATE Customers SET phone = '1-801-EAT-CAKE' WHERE first_name = 'Melamie' AND last_name = 'Feighry';

-- Delete Reservations for Horizon with Only One Ticket
DELETE FROM Reservations WHERE ticket_id IN (
    SELECT t.ticket_id FROM Tickets t
    JOIN Shows s ON t.show_id = s.show_id
    JOIN Theaters th ON s.theater_id = th.theater_id
    WHERE th.name = 'Horizon'
);

SELECT * from reservations;
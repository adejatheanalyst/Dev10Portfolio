CREATE DATABASE dont_wreck_my_house;
use dont_wreck_my_house;
-- Create the ability to store dishes that the restaurant is capable of making.
    -- Add new dishes to the database
    -- List all the dishes in our database, including price, name, and description




CREATE table guests (
    guest_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number varchar(255) NOT NULL
);
CREATE TABLE host(
    host_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number varchar(255) NOT NULL
);



CREATE table location (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(125) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    standard_rate DECIMAL(10, 2) NOT NULL,
    weekend_rate DECIMAL(10, 2) NOT NULL,
    host_id INT not null,
    constraint fk_location_host_id
        foreign key (host_id) 
        references host(host_id)
);



create table reservation(

    reservation_id INT PRIMARY KEY AUTO_INCREMENT,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL,
    location_id INT NOT NULL,
    guest_id INT NOT NULL,
    constraint fk_reservation_location_id
        foreign key (location_id)
        references location(location_id),
    constraint fk_reservation_guest_id
        foreign key (guest_id)
        references guests(guest_id)
);

    -- ALTER TABLE reservation
   --  ADD FOREIGN KEY fk_reservation_guest_id;

   --  ALTER table location
       -- DROP FOREIGN KEY fk_location_host_id;


);


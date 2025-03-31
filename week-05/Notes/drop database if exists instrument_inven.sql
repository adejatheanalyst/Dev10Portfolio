drop database if exists instrument_inventory_test;
create database instrument_inventory_test;
use instrument_inventory_test;


CREATE TABLE instruments(
    instrument_id INT PRIMARY KEY auto_increment,
    serial_number varchar(255) NOT NULL UNIQUE,
    instrumentType enum ("FLUTE",
    "CLARINET",
    "ALTO_SAXOPHONE",
    "TENOR_SAXOPHONE",
    "BARITONE_SAXOPHONE",
    "TRUMPET",
    "TROMBONE",
    "TUBA",
    "VIOLIN",
    "VIOLA",
    "CELLO",
    "UKULELE",
    "GUITAR",
    "PERCUSSION",
    "PIANO") NOT NULL,
    cost int NOT NULL,
    student_name TEXT NOT NULL,
    needs_repair BOOLEAN NOT NULL DEFAULT FALSE
);


delimiter //
create procedure set_known_good_state()
begin

    delete from instruments;
    ALTER table instruments AUTO_INCREMENT = 1;

    insert into instruments(serial_number, instrument_type, cost, student_name, needs_repair) values
    ('msj111','TUBA',400,'Michael',true),
    ('wow129','PIANO',1200,'Alyssa',true),
    ('1a2b3b4c5d','VIOLIN',400,'Brandi',true);

end //
delimiter ;

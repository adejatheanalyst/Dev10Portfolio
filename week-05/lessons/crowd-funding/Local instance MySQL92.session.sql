drop database if exists instrument_inventory;
create database instrument_inventory;
use instrument_inventory;

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




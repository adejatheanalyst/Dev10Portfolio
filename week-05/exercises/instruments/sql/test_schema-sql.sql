drop database if exists instrument_inventory_test;
create database instrument_inventory_test;
use instrument_inventory_test;


CREATE TABLE instruments(
    instrument_id INT PRIMARY KEY auto_increment,
    serialNumber varchar(255) NOT NULL UNIQUE,
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
    "PIANO"),
    cost int not null,
    student varchar(255) not null,
    needsRepair boolean not null default false
    );
    
insert into instruments(serialNumber, instrumentType, cost, student, needsRepair) values
    ('msj111','TUBA',400,'Michael',true),
    ('wow129','PIANO',1200,'Alyssa',true),
    ('1a2b3b4c5d','VIOLIN',400,'Brandi',true);

delimiter //
create procedure set_known_good_state()
begin
    delete from instruments;
    ALTER table instruments AUTO_INCREMENT = 1;

    insert into instruments(serialNumber, instrumentType, cost, student, needsRepair) values
    ('msj111','TUBA',400,'Michael',true),
    ('wow129','PIANO',1200,'Alyssa',true),
    ('1a2b3b4c5d','VIOLIN',400,'Brandi',true);

end //
delimiter ;
select * from instruments;
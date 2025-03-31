DROP DATABASE IF EXISTS instrument_inventory_test;
CREATE DATABASE instrument_inventory_test;
USE instrument_inventory_test;

CREATE TABLE instruments (
	instrument_id INT PRIMARY KEY AUTO_INCREMENT,
    serial_number VARCHAR(255) UNIQUE NOT NULL,
    `type` TEXT NOT NULL,
	cost INT NOT NULL,
    student TEXT NOT NULL,
    needs_repair BOOLEAN NOT NULL
);

delimiter //
create procedure set_known_good_state()
begin
    delete from instruments;
    alter table instruments auto_increment = 1;

    insert into instruments (serial_number, `type`, cost, student, needs_repair)
        values
        ('ZX452', 'FLUTE', 125, 'Bianca Sanders', false),
        ('sv15', 'CLARINET', 1400, 'Stepha', true),
        ('nyc170', 'PIANO', 1800, 'Shea', true);
                
end//
delimiter ;

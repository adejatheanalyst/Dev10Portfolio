drop database if exists solar_panel_test;
create database solar_panel_test;
use solar_panel_test;

create table solar_panels(
id int primary key auto_increment,
section varchar(255),
`row` int not null,
`column` int not null,
yearInstalled int not null,
material varchar(128),
isTracking boolean not null default false
);


insert into solar_panels(section, `row`, `column`, yearInstalled, material, isTracking) values
('The Ridge', 1, 1, 2000, 'Monocrystalline Silicon', true),
('The Ridge', 1, 2, 1999, 'Amorphous Silicon', false),
('Hill', 1, 3, 2010, 'Amorphous Silicon', false),
('Hill', 1, 4, 2022, 'Copper Indium Gallium Selenide', true),
('Range', 3, 5, 2010, 'Cadmium Telluride', true);

SHOW COLUMNS FROM solar_panels LIKE 'material';

SELECT * from solar_panels;


delimiter //
create procedure set_known_good_state()
begin
    delete from solar_panels;
    ALTER table solar_panels AUTO_INCREMENT = 1;

    insert into solar_panels(section, `row`, `column`, yearInstalled, material, isTracking) values
('The Ridge', 1, 1, 2000, 'Monocrystalline Silicon', true),
('The Ridge', 1, 2, 1999, 'Amorphous Silicon', false),
('Hill', 1, 3, 2010, 'Amorphous Silicon', false),
('Hill', 1, 4, 2022, 'Copper Indium Gallium Selenide', true),
('Range', 3, 5, 2010, 'Cadmium Telluride', true);

end //
delimiter ;







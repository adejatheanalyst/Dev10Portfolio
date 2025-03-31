drop database if exists solar_farm;
create database solar_farm;
use solar_farm;



create table solar_panels(
id int primary key auto_increment,
section varchar(255),
`row` int not null,
`column` int not null,
yearInstalled int not null,
material varchar(128),
isTracking boolean not null default false
);

SELECT * from solar_panels;

insert into solar_panels(section, `row`, `column`, yearInstalled, material, isTracking) values
('The Ridge', 1, 1, 2000, 'Monocrystalline Silicon', true),
('The Ridge', 1, 2, 1999, 'Multicrystalline Silicon', false),
('Hill', 1, 3, 2010, 'Amorphous Silicon', false),
('Hill', 1, 4, 2022, 'Copper Indium Gallium Selenide', true),
('Range', 3, 5, 2010, 'Cadmium Telluride', true);


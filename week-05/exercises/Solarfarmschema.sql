drop database if exists solar_farm;
create database solar_farm;
use solar_farm;


create table solar_panels(
solar_panel_id int primary key auto_increment,
section varchar(255),
`row` int not null,
col int not null,
year_installed year not null,
material enum('Monocrystalline Silicon', 'Multicrystalline Silicon', 'Amorphous Silicon', 'Copper Indium Gallium Selenide', 'Cadmium Telluride') not null,
is_tracked boolean
);

-- everything
-- solar panel types
-- types of solar panels
insert into solar_panels(section, `row`, col, year_installed, material, is_tracked) values
('The Ridge', 1, 1, 2000, 'Monocrystalline Silicon', true),
('The Ridge', 1, 2, 1999, 'Amorphous Silicon', false),
('Hill', 1, 3, 2010, 'Amorphous Silicon', false),
('Hill', 1, 4, 2022, 'Copper Indium Gallium Selenide', true),
('Range', 3, 5, 2010, 'Cadmium Telluride', true);


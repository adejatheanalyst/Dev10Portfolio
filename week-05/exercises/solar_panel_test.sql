drop database if exists solar_panel_test;
create database solar_panel_test;
use solar_panel_test;



create table solar_panels(
solar_panel_id int primary key auto_increment,
section varchar(255),
`row` int not null,
col int not null,
year_installed int not null,
material varchar(128),
is_tracked boolean not null default false
);

SELECT * from solar_panels;

insert into solar_panels(section, `row`, col, year_installed, material, is_tracked) values
('The Ridge', 1, 1, 2000, 'Monocrystalline', true),
('The Ridge', 1, 2, 1999, 'Multicrystaline', false),
('Hill', 1, 3, 2010, 'Amorphous Silicon', false),
('Hill', 1, 4, 2022, 'CIGS', true),
('Range', 3, 5, 2010, 'Cadmium Telluride', true);






delimiter //
create procedure set_known_good_state()
begin
	delete from solar_panels;
    ALTER table solar_panels AUTO_INCREMENT = 1;

    insert into solar_panels(section, `row`, col, year_installed, material, is_tracked) values
    ('test-section', 1, 1, 2000, 'Monocrystalline', true),
    ('test-section', 1, 2, 2000, 'Multicrystaline', false),
    ('test-section', 1, 3, 2000, 'Amorphous Silicon', false),
    ('test-section', 1, 4, 2000, 'CIGS', true),
    ('test-section', 3, 5, 2000, 'Cadmium Telluride', true);
        
end//
delimiter ;











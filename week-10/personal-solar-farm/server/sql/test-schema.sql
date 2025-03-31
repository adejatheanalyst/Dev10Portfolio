drop database if exists solar_test;
create database solar_test;
use solar_test;


create table user(
user_id int primary key auto_increment,
username varchar(50) unique,
`password` text
);
create table panel (
	panel_id int primary key auto_increment,
    section varchar(50) not null,
    `row` int not null,
    `column` int not null,
    year_installed int not null,
    material varchar(50) not null,
    is_tracking boolean not null,
     user_id int not null,
    foreign key(user_id) references `user`(user_id),
    constraint uq_section_row_column
		unique(section, `row`, `column`)
);

delimiter //
create procedure set_known_good_state()
begin

	delete from panel;
    alter table panel auto_increment = 1;
    delete from `user`;
    alter table `user` auto_increment = 1;
    
	insert into `user`(username, `password`)
		values
		('testUsername', 'testPassword'),
		('testUsername2', 'testPassword2');
        
    insert into panel (section, `row`, `column`, year_installed, material, is_tracking, user_id)
		values
        ('red', 1, 1, 2020, 'MONO_SI', true, 1),
        ('red', 1, 2, 2020, 'MONO_SI', true, 1),
        ('red', 1, 3, 2020, 'MONO_SI', true, 1),
        ('blue', 4, 1, 2020, 'MONO_SI', true, 1),
        ('blue', 5, 1, 2020, 'MONO_SI', true, 1);
        
end//
delimiter ;
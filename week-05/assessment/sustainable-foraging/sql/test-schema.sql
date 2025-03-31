drop database if exists sustainable_foraging_test;
create database sustainable_foraging_test;
use sustainable_foraging_test;

create table forager (
	forager_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    state_abbr varchar(2) not null
);

create table item (
	item_id int primary key auto_increment,
    `name` varchar(50) not null,
    category varchar(50) not null,
    dollars_per_kilogram decimal(10,2) not null
);

create table forage (
	forage_id int primary key auto_increment,
    `date` date not null,
    amount decimal(8,2) not null,
    forager_id int not null,
    item_id int not null,
    constraint fk_forage_forager_id
		foreign key (forager_id)
        references forager(forager_id),
	constraint fk_forage_item_id
		foreign key (item_id)
        references item(item_id),
	constraint uq_forage_date_forager_item
		unique(`date`, forager_id, item_id)
);

delimiter //
create procedure set_known_good_state()
begin
	delete from forage;
	delete from item;
	delete from forager;
	alter table forage auto_increment=1;
	alter table item auto_increment=1;
	alter table forager auto_increment=1;
    
    insert into item (`name`, category, dollars_per_kilogram) values
		('EDIBLE', 'EDIBLE', 10.0),
        ('MEDICINAL', 'MEDICINAL', 10.0),
        ('INEDIBLE', 'INEDIBLE', 0.0),
        ('POISONOUS', 'POISONOUS', 0.0);
        
	insert into forager (first_name, last_name, state_abbr) values
		('First 1','Last 1','OR'),
        ('First 2','Last 2','TX');
        
	insert into forage (`date`, amount, forager_id, item_id) values
		('2023-01-01', 1.0, 1, 1),
        ('2023-01-01', 1.0, 2, 2),
        ('2023-01-01', 1.0, 1, 3);
end//
delimiter ;
drop database if exists sustainable_foraging;
create database sustainable_foraging;
use sustainable_foraging;

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
drop database if exists pets;
create database pets;
use pets;

create table pet (
    pet_id int primary key auto_increment,
    `name` varchar(255) not null,
    breed varchar(255) not null,
	dob date null,
	adopted boolean not null,
	image_url text null,
    vaccination_status varchar(255) not null,
    pet_type varchar(255) not null
);

insert into pet (name, pet_type, breed, dob, adopted, vaccination_status, image_url)
    values
('Jimmy', 'CAT', 'American Domestic Shorthair', '2007-07-07', true, 'UP_TO_DATE', 'https://i.imgur.com/mebjgyH.jpg'),
('Gordon', 'CAT', 'American Domestic Shorthair', '2008-11-11', true, 'NOT_UP_TO_DATE', 'https://i.imgur.com/VqMXfEn.jpg'),
('Diego', 'CAT', 'American Domestic Shorthair', null, true, 'NOT_UP_TO_DATE', 'https://i.imgur.com/QtNyn5i.jpg'),
('Lionel', 'CAT', 'American Domestic Shorthair', null, true, 'UNKNOWN', 'https://i.imgur.com/IkBMGi8.jpg'),
('Wishbone', 'DOG', 'Jack Russell Terrier', '2015-05-05', true, 'UNKNOWN', 'https://i.imgur.com/yGzjvPj.jpg'),
('Whiskers', 'CAT', 'Tabby', '2020-01-01', false, 'UP_TO_DATE', 'https://i.imgur.com/vlnDvGW.jpg'),
('Archie', 'DOG', 'Golden Retriever', '2022-12-15', false, 'NOT_UP_TO_DATE', 'https://i.imgur.com/IeR2bMU.jpg'),
('Penny', 'CAT', 'Tabby', '2016-02-01', true, 'UP_TO_DATE', 'https://i.imgur.com/GOS9SHn.jpg');

select * from pet;

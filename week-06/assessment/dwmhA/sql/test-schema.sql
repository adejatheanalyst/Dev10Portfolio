drop database if exists dwmh_test;
create database dwmh_test;
use dwmh_test;


select * from location;
select * from user;
select * from state;
select * from reservation;

create table state (
	state_id int primary key auto_increment,
    `name` varchar(50) not null unique,
    usps_code varchar(2) not null unique
);

create table `user` (
	user_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(512) not null unique,
    phone varchar(50) not null    
);

create table location (
	location_id int primary key auto_increment,
	user_id int not null,
    address varchar(100) not null,
    city varchar(100) not null,
	postal_code varchar(20) not null,
	state_id int not null,
    standard_rate decimal(8, 2) not null,
	weekend_rate decimal(8, 2) not null,
    constraint fk_location_user_id
        foreign key (user_id)
        references user(user_id),
    constraint fk_location_state_id
        foreign key (state_id)
        references state(state_id)            
);

create table reservation (
	reservation_id int primary key auto_increment,
	location_id int not null,
    guest_user_id int not null,
    start_date date not null,
    end_date date not null,
    total decimal(10, 2) not null,
    constraint fk_reservation_location_id
        foreign key (location_id)
        references location(location_id),
    constraint fk_reservation_guest_user_id
        foreign key (guest_user_id)
        references user(user_id)    
);

insert into state(`name`, usps_code) values
	('Alabama','AL'),
	('Alaska','AK'),
	('Arizona','AZ'),
	('Arkansas','AR'),
	('California','CA'),
	('Colorado','CO'),
	('Connecticut','CT'),
	('Delaware','DE'),
	('Florida','FL'),
	('Georgia','GA'),
	('Hawaii','HI'),
	('Idaho','ID'),
	('Illinois','IL'),
	('Indiana','IN'),
	('Iowa','IA'),
	('Kansas','KS'),
	('Kentucky','KY'),
	('Louisiana','LA'),
	('Maine','ME'),
	('Maryland','MD'),
	('Massachusetts','MA'),
	('Michigan','MI'),
	('Minnesota','MN'),
	('Mississippi','MS'),
	('Missouri','MO'),
	('Montana','MT'),
	('Nebraska','NE'),
	('Nevada','NV'),
	('New Hampshire','NH'),
	('New Jersey','NJ'),
	('New Mexico','NM'),
	('New York','NY'),
	('North Carolina','NC'),
	('North Dakota','ND'),
	('Ohio','OH'),
	('Oklahoma','OK'),
	('Oregon','OR'),
	('Pennsylvania','PA'),
	('Rhode Island','RI'),
	('South Carolina','SC'),
	('South Dakota','SD'),
	('Tennessee','TN'),
	('Texas','TX'),
	('Utah','UT'),
	('Vermont','VT'),
	('Virginia','VA'),
	('Washington','WA'),
	('West Virginia','WV'),
	('Wisconsin','WI'),
	('Wyoming','WY');
	
    insert into `user` (first_name, last_name, email, phone) values
	('Llywellyn','Vondra','lvondra0@vkontakte.ru','(940) 3612277'),
	('Roanna','Klimpt','rklimpt1@paginegialle.it','(277) 2824355'),
	('Teresina','Honnan','thonnan2@berkeley.edu','(353) 2288123'),
	('Dominik','Bestar','dbestar3@yelp.com','(221) 9350388'),
	('Andree','Lardeux','alardeux4@nhs.uk','(185) 1958113'),
    ('Brenn','Biesterfeld','bbiesterfeld5@1und1.de','(693) 7271508'),
	('Florencia','Farnall','ffarnall6@columbia.edu','(105) 4474058'),
	('Shelbi','Jagger','sjagger7@latimes.com','(185) 3989605'),
	('Budd','Houliston','bhouliston8@mozilla.com','(571) 2297635'),
	('Jerrilyn','Carnew','jcarnew9@epa.gov','(763) 7027565');
    
    insert into location (user_id, address, city, postal_code, state_id, standard_rate, weekend_rate) values
	(1,'6497 Manitowish Circle','Melbourne','32919',(select state_id from state where usps_code = 'FL'),110,127),
	(2,'82309 Hermina Court','Santa Cruz','95064',(select state_id from state where usps_code = 'CA'),245,263),
	(3,'302 Wayridge Hill','Atlanta','30351',(select state_id from state where usps_code = 'GA'),290,304),
	(5,'1303 Shopko Alley','Panama City','32412',(select state_id from state where usps_code = 'FL'),184,218),
	(4,'2722 Trailsway Place','Kansas City','64142',(select state_id from state where usps_code = 'MO'),288,322);

    insert into reservation (location_id, guest_user_id, start_date, end_date, total) values
    ((select location_id from location where user_id = 1), (select user_id from `user` where first_name = 'Llywellyn'), '2023-01-01', '2023-01-03', 237.00),
    ((select location_id from location where user_id = 3), (select user_id from  `user` where first_name = 'Teresina'), '2023-01-03', '2023-01-05', 594.00),
    ((select location_id from location where user_id = 4), (select user_id from  `user` where first_name = 'Dominik'), '2023-03-01', '2023-03-03', 610.00),
    ((select location_id from location where user_id = 5), (select user_id from  `user` where first_name = 'Andree'), '2023-05-01', '2023-05-03', 506.00),
    ((select location_id from location where user_id = 2), (select user_id from  `user` where first_name = 'Roanna'), '2023-10-01', '2023-10-03', 516.00);

delimiter //
create procedure set_known_good_state()
begin
	delete from reservation;
	delete from location;
	delete from `user`;
    delete from state;
	alter table reservation auto_increment=1;
	alter table location auto_increment=1;
	alter table `user` auto_increment=1;
    alter table state auto_increment=1;
    
	insert into state(`name`, usps_code) values
	('Alabama','AL'),
	('Alaska','AK'),
	('Arizona','AZ'),
	('Arkansas','AR'),
	('California','CA'),
	('Colorado','CO'),
	('Connecticut','CT'),
	('Delaware','DE'),
	('Florida','FL'),
	('Georgia','GA'),
	('Hawaii','HI'),
	('Idaho','ID'),
	('Illinois','IL'),
	('Indiana','IN'),
	('Iowa','IA'),
	('Kansas','KS'),
	('Kentucky','KY'),
	('Louisiana','LA'),
	('Maine','ME'),
	('Maryland','MD'),
	('Massachusetts','MA'),
	('Michigan','MI'),
	('Minnesota','MN'),
	('Mississippi','MS'),
	('Missouri','MO'),
	('Montana','MT'),
	('Nebraska','NE'),
	('Nevada','NV'),
	('New Hampshire','NH'),
	('New Jersey','NJ'),
	('New Mexico','NM'),
	('New York','NY'),
	('North Carolina','NC'),
	('North Dakota','ND'),
	('Ohio','OH'),
	('Oklahoma','OK'),
	('Oregon','OR'),
	('Pennsylvania','PA'),
	('Rhode Island','RI'),
	('South Carolina','SC'),
	('South Dakota','SD'),
	('Tennessee','TN'),
	('Texas','TX'),
	('Utah','UT'),
	('Vermont','VT'),
	('Virginia','VA'),
	('Washington','WA'),
	('West Virginia','WV'),
	('Wisconsin','WI'),
	('Wyoming','WY');
	
    insert into `user` (first_name, last_name, email, phone) values
	('Llywellyn','Vondra','lvondra0@vkontakte.ru','(940) 3612277'),
	('Roanna','Klimpt','rklimpt1@paginegialle.it','(277) 2824355'),
	('Teresina','Honnan','thonnan2@berkeley.edu','(353) 2288123'),
	('Dominik','Bestar','dbestar3@yelp.com','(221) 9350388'),
	('Andree','Lardeux','alardeux4@nhs.uk','(185) 1958113'),
    ('Brenn','Biesterfeld','bbiesterfeld5@1und1.de','(693) 7271508'),
	('Florencia','Farnall','ffarnall6@columbia.edu','(105) 4474058'),
	('Shelbi','Jagger','sjagger7@latimes.com','(185) 3989605'),
	('Budd','Houliston','bhouliston8@mozilla.com','(571) 2297635'),
	('Jerrilyn','Carnew','jcarnew9@epa.gov','(763) 7027565');
    
    
    insert into location (user_id, address, city, postal_code, state_id, standard_rate, weekend_rate) values
	(1,'6497 Manitowish Circle','Melbourne','32919',(select state_id from state where usps_code = 'FL'),110,127),
	(2,'82309 Hermina Court','Santa Cruz','95064',(select state_id from state where usps_code = 'CA'),245,263),
	(3,'302 Wayridge Hill','Atlanta','30351',(select state_id from state where usps_code = 'GA'),290,304),
	(5,'1303 Shopko Alley','Panama City','32412',(select state_id from state where usps_code = 'FL'),184,218),
	(4,'2722 Trailsway Place','Kansas City','64142',(select state_id from state where usps_code = 'MO'),288,322);


    insert into reservation (location_id, guest_user_id, start_date, end_date, total) values
  ((select location_id from location where user_id = 1), (select user_id from `user` where first_name = 'Llywellyn'), '2023-01-01', '2023-01-03', 237.00),
  ((select location_id from location where user_id = 3), (select user_id from  `user` where first_name = 'Teresina'), '2023-01-03', '2023-01-05', 594.00),
  ((select location_id from location where user_id = 4), (select user_id from  `user` where first_name = 'Dominik'), '2023-03-01', '2023-03-03', 610.00),
  ((select location_id from location where user_id = 5), (select user_id from  `user` where first_name = 'Andree'), '2023-05-01', '2023-05-03', 506.00),
  ((select location_id from location where user_id = 2), (select user_id from  `user` where first_name = 'Roanna'), '2023-10-01', '2023-10-03', 516.00);
    end//
    delimiter ;


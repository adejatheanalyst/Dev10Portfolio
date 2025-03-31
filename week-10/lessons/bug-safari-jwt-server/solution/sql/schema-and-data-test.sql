drop database if exists bug_safari_test;
create database bug_safari_test;
use bug_safari_test;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled bit not null default(1)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

create table bug_order (
	bug_order_id int primary key auto_increment,
    `name` varchar(50) not null,
    `description` varchar(250) not null
);

create table sighting (
	sighting_id int primary key auto_increment,
    bug_type varchar(50) not null,
    `description` text,
    sighting_date date not null,
    interest double not null,
    image_url varchar(250) null,
    bug_order_id int not null,
    constraint fk_sighting_bug_order_id
		foreign key (bug_order_id)
		references bug_order(bug_order_id)
);

delimiter //

create procedure set_known_good_state()
begin

	delete from app_user_role;
	delete from app_user;
	alter table app_user auto_increment = 1;
	delete from app_role;
	alter table app_role auto_increment = 1;
	delete from sighting;
	alter table sighting auto_increment = 1;
	delete from bug_order;
	alter table bug_order auto_increment = 1;

	-- data
    
    insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

	-- passwords are set to "P@ssw0rd!"
	insert into app_user (username, password_hash, enabled)
		values
		('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
		('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1);

	insert into app_user_role
		values
		(1, 2),
		(2, 1);
    
	insert into bug_order (bug_order_id, `name`, `description`) values
		(1, 'Coleptera', 'beetles'),
		(2, 'Lepidoptera', 'butterflies, moths'),
		(3, 'Hymenoptera', 'ants, bees, wasps');

	insert into sighting(bug_type, `description`, sighting_date, interest, image_url, bug_order_id) values
		('Ladybug', 'mature ladybug in the grass',
		'2020-08-12', 5.5,
		'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Coccinella_magnifica01.jpg/640px-Coccinella_magnifica01.jpg',
		1),
		('Cicadas', 'the cicadas are singing in the trees',
		'2020-09-12', 7.0,
		'https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Neotibicen_linnei.jpg/536px-Neotibicen_linnei.jpg',
		2),
		('Darkling Beetle', 'found an interesting beetle crawling in the compost',
		'2020-09-11', 9.75,
		'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Darkling_beetle.jpg/640px-Darkling_beetle.jpg',
		1);

end //

delimiter ;


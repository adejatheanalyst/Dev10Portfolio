drop database if exists crowd_funding_test;
create database crowd_funding_test;
use crowd_funding_test;

create table campaign (
	campaign_id int primary key auto_increment,
    `name` varchar(128) not null unique,
    `description` varchar(1024) not null,
    start_date date not null,
    end_date date not null,
    funding_goal decimal(10,2) not null
);

create table backer (
	backer_id int primary key auto_increment,
    `name` varchar(128) not null,
    email_address varchar(512) not null unique
);

create table pledge (
	pledge_id int primary key auto_increment,
    campaign_id int not null,
    backer_id int not null,
    amount decimal(10,2) not null,
    created_on datetime not null default current_timestamp,
    constraint fk_pledge_campaign_id
		foreign key(campaign_id)
        references campaign(campaign_id),
	constraint fk_pledge_backer_id
		foreign key(backer_id)
        references backer(backer_id),
	constraint uq_campaign_id_backer_id
		unique(campaign_id, backer_id)
);

delimiter //
create procedure set_known_good_state()
begin
	delete from pledge;
	delete from backer;
	delete from campaign;
	alter table pledge auto_increment=1;
	alter table backer auto_increment=1;
	alter table campaign auto_increment=1;
    
    insert into campaign (`name`, `description`, start_date, end_date, funding_goal)
		values
		('Campaign #1', 'Description #1', adddate(curdate(), interval -60 day), adddate(curdate(), interval -30 day), 30000),
        ('Campaign #2', 'Description #2', adddate(curdate(), interval -30 day), adddate(curdate(), interval 30 day), 30000);
    
	insert into backer (`name`, email_address) values
		('Backer #1', 'backer1@example.com'),
		('Backer #2', 'backer2@example.com');
    
	insert into pledge(campaign_id, backer_id, amount) values
		(1, 1, 100),
        (1, 2, 100),
        (2, 1, 100);
        
end//
delimiter ;
drop database if exists crowd_funding;
create database crowd_funding;
use crowd_funding;

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
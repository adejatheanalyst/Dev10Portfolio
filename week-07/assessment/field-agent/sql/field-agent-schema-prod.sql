drop database if exists field_agent;
create database field_agent;
use field_agent;

-- create tables and relationships
create table agency (
    agency_id int primary key auto_increment,
    short_name varchar(25) not null,
    long_name varchar(250) not null
);

create table location (
    location_id int primary key auto_increment,
    `name` varchar(25) not null,
    address varchar(125) not null,
    city varchar(50) not null,
	region varchar(25) null,
    country_code varchar(5) not null,
    postal_code varchar(15) not null,
    agency_id int not null,
    constraint fk_location_agency_id
        foreign key (agency_id)
        references agency(agency_id)
);

create table agent (
    agent_id int primary key auto_increment,
    first_name varchar(50) not null,
    middle_name varchar(50) null,
    last_name varchar(50) not null,
    dob date null,
    height_in_inches int not null
);

create table security_clearance (
    security_clearance_id int primary key auto_increment,
    `name` varchar(50) not null
);

create table mission (
    mission_id int primary key auto_increment,
    code_name varchar(50) not null,
    notes text,
    start_date date not null,
    projected_end_date date not null,
    actual_end_date date null,
    operational_cost decimal(10,2) not null,
    agency_id int not null,
    constraint fk_mission_agency_id
        foreign key(agency_id)
        references agency(agency_id)
);

create table agency_agent (
    agency_id int not null,
    agent_id int not null,
    identifier varchar(50) not null,
    security_clearance_id int not null,
    activation_date date not null,
    is_active bit not null default 1,
    constraint pk_agency_agent
        primary key (agency_id, agent_id),
    constraint fk_agency_agent_agency_id
        foreign key (agency_id)
        references agency(agency_id),
    constraint fk_agency_agent_agent_id
        foreign key (agent_id)
        references agent(agent_id),
    constraint fk_agency_agent_security_clearance_id
        foreign key (security_clearance_id)
        references security_clearance(security_clearance_id),
    constraint uq_agency_agent_identifier_agency_id
        unique (identifier, agency_id)
);

create table mission_agent (
    mission_id int not null,
    agent_id int not null,
    constraint pk_mission_agent
        primary key(mission_id, agent_id),
    constraint fk_mission_agent_mission_id
        foreign key (mission_id)
        references mission(mission_id),
    constraint fk_mission_agent_agent_id
        foreign key (agent_id)
        references agent(agent_id)
);

create table alias (
    alias_id int primary key auto_increment,
    `name` varchar(125) not null,
    persona varchar(2048) null,
    agent_id int not null,
    constraint fk_alias_agent_id
        foreign key (agent_id)
        references agent(agent_id)
);

-- data
insert into security_clearance values
	(1, 'Secret'),
    (2, 'Top Secret');
    
    insert into agent 
		(first_name, middle_name, last_name, dob, height_in_inches) 
	values
		('Hazel','C','Sauven','1954-09-16',76),
		('Claudian','C','O''Lynn','1956-11-09',41),
		('Winn','V','Puckrin','1999-10-21',60),
		('Kiab','U','Whitham','1960-07-29',52),
		('Min','E','Dearle','1967-04-18',44),
		('Urban','H','Carwithen',null,58),
		('Ulises','B','Muhammad','2008-04-01',80),
		('Phylys','Y','Howitt','1979-03-28',68);

use rcttc;

rename table rcttc_data to temp_rcttc_data;
alter table temp_rcttc_data
change column `show` show_title varchar(255) not null;

select * from  temp_rcttc_data;
create table theaters (
	theater_id int primary key auto_increment unique,
	theater_name varchar(255),
	theater_address varchar(255),
	theater_phone varchar(255),
	theater_email varchar(255) unique
);


create table shows(
	show_id int primary key auto_increment unique,
	show_title varchar(50),
	ticket_price decimal(8,2),
	date date,
    theater_id int not null,
constraint fk_theater_id
    foreign key (theater_id)
    references theaters(theater_id)
);

create table customers (
	customer_id int primary key auto_increment unique,
	customer_first varchar(50),
	customer_last varchar(50),
	customer_email varchar (255) unique,
	customer_phone varchar(255) null,
	customer_address varchar(255) null,
    seat varchar(255),
    date date,
    show_id int not null,
    constraint fk_customer_show_id
        foreign key (show_id)
        references shows(show_id)
);

create table theater_seats(
    seat_id int primary key auto_increment unique,
    seat varchar(255),
    date date,
    customer_id int not null,
    theater_id int not null,
    constraint fk_customer_seat_id
        foreign key (customer_id)
        references customers(customer_id),
    constraint fk_theater_seat_id
        foreign key (theater_id)
        references theaters(theater_id)
);


create table reservations (
        customer_id int not null,
        customer_first varchar(50) not null,
        customer_last varchar(50) not null,
        customer_email varchar(255) not null,
        customer_phone varchar(255) null,
        customer_address varchar(255) null,
        show_id int not null,
        show_title varchar(50) not null,
        ticket_price decimal(8,2) not null,
        seat_id int not null,
        seat varchar(25) not null,
        date date not null,
        theater_id int not null,
        theater_name varchar(50) not null,
        theater_address varchar(255) not null,
        theater_phone varchar(255) not null,
        theater_email varchar(255) not null,
        constraint pk_rcttc_data
            primary key (customer_id, theater_id, show_id),
        constraint fk_rcttc_data_customer_id
            foreign key (customer_id)
            references customers(customer_id),
        constraint fk_rcttc_data_theater_id
            foreign key (theater_id)
            references theaters(theater_id),
        constraint fk_rcttc_data_show_id
            foreign key (show_id)
            references shows(show_id),
        constraint fk_res_seat_id
            foreign key (seat_id)
            references theater_seats(seat_id)
);

SELECT * from reservations;


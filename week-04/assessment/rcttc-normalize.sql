use rcttc;

SELECT * from temp_rcttc_data;
insert into theaters (theater_name, theater_address, theater_phone, theater_email)
select distinct theater, theater_address, theater_phone, theater_email
from temp_rcttc_data;


 SELECT * FROM theaters;


insert into shows (show_title, ticket_price, date, theater_id)
select distinct show_title, ticket_price, date, theater_id
from (
	select show_title, ticket_price, date, theaters.theater_id as theater_id,
    row_number() over(partition by td.show_title order by td.date) as sn
	from temp_rcttc_data td
	inner join theaters on td.theater = theaters.theater_name
) as subquery
where sn = 1;

SELECT * from shows;


insert into customers (customer_first, customer_last, customer_email, customer_phone, customer_address, seat, date,  show_id)
select distinct customer_first, customer_last, customer_email, customer_phone, customer_address, seat,  date,  show_id 
from (
    
    select customer_first, customer_last, customer_email, customer_phone, customer_address, seat, td.date,  shows.show_id as show_id,
    row_number() over(partition by td.customer_first order by td.seat) as rn
    from temp_rcttc_data td
    inner join shows on td.show_title = shows.show_title
) as subquery
where rn = 1;
SELECT * from customers;


insert into theater_seats(seat, date, customer_id, theater_id)
select distinct seat, date, customer_id, theater_id
from(
    select td.seat, td.date, customers.customer_id as customer_id, t.theater_id as theater_id
    from temp_rcttc_data td
    inner join customers on td.customer_first = customers.customer_first
    inner join theaters t on td.theater = t.theater_name
) as subquery1;

SELECT * from theater_seats;


insert into reservations (
    customer_id,
    customer_first,
    customer_last,
    customer_email,
    customer_phone,
    customer_address,
    show_id,
    show_title,
    ticket_price,
    seat_id,
    seat,
    date,
    theater_id,
    theater_name,
    theater_address,
    theater_phone,
    theater_email
)
select distinct
    customers.customer_id as customer_id,
    td.customer_first, 
    td.customer_last,
    td.customer_email,
    td.customer_phone,
    td.customer_address,
    shows.show_id as show_id,
    td.show_title,
    td.ticket_price,
    ts.seat_id as seat_id,
    td.seat,
    td.date,
    theaters.theater_id as theater_id,
    td.theater as theater_name,
    td.theater_address,
    td.theater_phone,
    td.theater_email
from temp_rcttc_data td
inner join customers on td.customer_first = customers.customer_first
inner join theaters on td.theater = theaters.theater_name
inner join shows on td.show_title = shows.show_title
inner join theater_seats ts on td.seat = ts.seat
on duplicate key update
    customer_first = values(customer_first),
    customer_last = values(customer_last),
    customer_email = values(customer_email),
    customer_phone = values(customer_phone),
    customer_address = values(customer_address),
    theater_name = values(theater_name),
    theater_phone = values(theater_phone),
    theater_email = values(theater_email),
    show_title = values(show_title),
    ticket_price = values(ticket_price),
    seat = values(seat),
    date = values(date);

SELECT * FROM reservations;

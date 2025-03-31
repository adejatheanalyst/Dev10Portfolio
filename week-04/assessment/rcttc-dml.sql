use rcttc;
update shows SET
ticket_price = 22.25
where show_title = 'The Sky Lit Up' and date = '2024-03-01';


SELECT * FROM
shows;


select
ts.seat_id,
ts.seat,
r.customer_id,
t.theater_id,
t.theater_name,
concat(r.customer_first, " ", r.customer_last) as full_name
from theater_seats ts
JOIN reservations r on ts.customer_id = r.customer_id
join theaters t on ts.theater_id = t.theater_id
where t.theater_name = 'Little Fitz'
;


update theater_seats 
set seat = 'B4'
where customer_id = '56' and seat_id = '147';
update reservations
set seat = 'B4'
where customer_id = '56' and seat_id = '147';


update theater_seats
set seat = 'C2'
where customer_id = '15' and seat_id = '35';
update reservations
set seat = 'C2'
where customer_id = '15' and seat_id = '35';


update theater_seats
set seat = 'A4'
where customer_id = '13' and seat_id = '31';
update reservations
set seat = 'A4'
where customer_id = '13' and seat_id = '31';

select
ts.seat_id,
ts.seat,
r.customer_id,
t.theater_id,
t.theater_name,
concat(r.customer_first, " ", r.customer_last) as full_name
from theater_seats ts
JOIN reservations r on ts.customer_id = r.customer_id
join theaters t on ts.theater_id = t.theater_id
where customer_first = 'Pooh' or customer_first = 'Cullen'
;



SELECT * FROM reservations
where customer_first = 'Jammie';

update customers
SET customer_phone = '1-801-EAT-CAKE'
where customer_first = 'Jammie' and customer_last = 'Swindles';

update reservations
SET customer_phone = '1-801-EAT-CAKE'
where customer_first = 'Jammie' and customer_last = 'Swindles';


select DISTINCT
ts.seat_id,
ts.seat,
r.customer_first,
ts.customer_id
from theater_seats ts
join reservations r on ts.customer_id = r.customer_id
where r.theater_name = '10 Pin'
GROUP BY r.customer_first, ts.seat_id
ORDER BY ts.customer_id asc
limit 100 ;
SELECT * from theaters;

SELECT * from reservations;


SELECT 
customer_first
seat,
customer_id,
theater_id,
seat_id
from 
reservations
where theater_id = 1;

alter table theater_seats
drop constraint fk_customer_seat_id;

alter table reservations
drop constraint fk_rcttc_data_customer_id;

select * from customers;

delete from customers 
where customer_id = 27;
delete from customers 
where customer_id = 9;
delete from customers 
where customer_id = 12;
delete from customers 
where customer_id = 19;
delete from customers 
where customer_id = 23;
delete from customers 
where customer_id = 31;
delete from customers 
where customer_id = 46;
delete from customers 
where customer_id = 48;
delete from customers 
where customer_id = 52;


delete from reservations 
where customer_id = 27;
delete from reservations 
where customer_id = 9;
delete from reservations 
where customer_id = 12;
delete from reservations 
where customer_id = 19;
delete from reservations 
where customer_id = 23;
delete from reservations 
where customer_id = 31;
delete from reservations 
where customer_id = 46;
delete from reservations 
where customer_id = 48;
delete from reservations 
where customer_id = 52;


select * from customers;

delete from customers 
where customer_id = 45;

delete from reservations 
where customer_id = 45;

select 
customer_id,
customer_first,
customer_last
from reservations
where customer_first = "Liv";
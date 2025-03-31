use rcttc;

-- Find all performances in the last quarter of 2024 (Oct. 1, 2024 - Dec. 31 2024).
select
show_title,
date
from shows
where date BETWEEN '2024-10-01' and '2024-12-31';


-- List customers without duplication.

SELECT DISTINCT concat(customer_first, " ", customer_last) as full_name
from customers;

-- Find all customers without a .com email address.
SELECT concat(customer_first, " ", customer_last) as full_name,
customer_email
from customers
where customer_email not like '%.com';

-- Find the three cheapest shows.
select 
ticket_price,
show_title
from shows
ORDER BY ticket_price ASC
limit 3;

-- List customers and the show they're attending with no duplication

SELECT DISTINCT 
customer_id,
concat(customer_first, " ", customer_last) as full_name,
show_title,
date
from reservations
GROUP BY customer_id, full_name,show_title, date
ORDER BY customer_id;

-- List customer, show, theater, and seat number in one query.

SELECT
concat(customer_first, " ", customer_last) as full_name,
show_title,
theater_name,
seat
from reservations;

-- Find customers without an address.
SELECT *
from customers
where customer_address = '' ;

-- Recreate the denormalized data with a single query.
select * from temp_rcttc_data;

SELECT
r.customer_first,
r.customer_last,
r.customer_email,
r.customer_phone,
r.customer_address,
ts.seat,
s.show_title,
s.ticket_price,
s.date,
t.theater_name,
t.theater_address,
t.theater_phone,
t.theater_email
from reservations r
join theater_seats ts on r.seat = ts.seat
join shows s on r.show_title = s.show_title
join theaters t on r.theater_name = t.theater_name
limit 194;

-- Count total tickets purchased per customer.
select * from shows;
SELECT * from theater_seats;

select
concat(customer_first, " ", customer_last) as full_name,
c.customer_id,
COUNT(ts.seat) as seat_count,
s.ticket_price * count(ts.seat) as total_per_customer
from reservations c
join shows s on c.show_id = s.show_id
join theater_seats ts on c.seat = ts.seat
GROUP BY C.customer_id;

-- Calculate the total revenue per show based on tickets sold.

select
s.show_id,
s.show_title,
COUNT(ts.seat) as ticket_sold,
s.ticket_price * count(ts.seat) as Total_revenue
from reservations c
join shows s on c.show_id = s.show_id
join theater_seats ts on c.seat = ts.seat
GROUP BY s.show_id;

-- Calculate the count (*total revenue per theater based on tickets sold.

select
t.theater_id,
t.theater_name,
COUNT(ts.seat) as ticket_sold,
s.ticket_price * count(ts.seat) as Total_revenue
from reservations c
join shows s on c.show_id = s.show_id
join theater_seats ts on c.seat = ts.seat
join theaters t on c.theater_id = t.theater_id
GROUP BY t.theater_id;


-- Who is the biggest supporter of RCTTC? Who spent the most in 2024?

select
concat(customer_first, " ", customer_last) as full_name,
c.customer_id,
COUNT(ts.seat) as seat_count,
s.ticket_price * count(ts.seat) as total_per_customer,
c.date
from reservations c
join shows s on c.show_id = s.show_id
join theater_seats ts on c.seat = ts.seat
where YEAR(c.date) = '2024'
GROUP BY C.customer_id
order by total_per_customer DESC;


use gravel_family;

-- Solve each task by writing a query below the task description.
-- Each task describes the expected result.
-- Unfortunately, task must be verified manually. :(

-- Example:
-- Select all columns from the employee table.
-- Expected: 33 Rows
select * from employee;

-- Select the employee_id, first_name, and last_name from employee.
-- Expected: 33 Rows, 3 columns
select
employee_id,
first_name,
last_name
from employee;
-- Select the employee_id, first_name, and start_date from employee
-- where the last_name equals 'Gravel'.
-- Expected: 7 Rows, 3 columns
select
employee_id,
first_name,
start_date
from employee
where last_name = "Gravel";
-- Select first_name, last_name, and city from customer
-- where the city equals 'Ajax'.
-- Expected: 13 Rows, 3 columns
select
first_name,
last_name,
city
from customer
where city = "Ajax";
-- Select last_name, email_address, and customer_since from customer
-- for all customers with a customer_since value in the year 2019.
-- Expected: 100 Rows, 3 columns
select * from customer;
select
last_name,
email_address,
customer_since
from customer
where customer_since between '2019-01-01' and '2019-12-31';

-- If you solved the previous task without `between`, use `between`.
-- If you used `between`, solve it with `and`.
-- Expected: 100 Rows, 3 columns

-- Find 2019 customers a third time, but this time sort them by customer_since descending.
-- Expected: 100 Rows, 3 columns
select
last_name,
email_address,
customer_since
from customer
where customer_since between '2019-01-01' and '2019-12-31' 
order by customer_since desc;

-- Select first_name, last_name, phone, and address from customer.
-- Sort by last_name descending and first_name ascending.
-- Expected: 1000 Rows, 4 columns
select
first_name,
last_name,
phone,
address
from customer
order by last_name desc, first_name asc;
-- Which customer city comes last in the alphabet?
-- Expected: Woodstock
select 
 city 
 from customer
order by city desc;
-- Select last_name, address, and city from customers
-- where cities are 'Toronto', 'Brampton', or 'Mississauga'.
-- Expected: 34 Rows, 3 columns
select 
 last_name, 
 address,
 city
 from customer
 where city in ('Toronto', 'Brampton' , 'Mississauga');
-- If you solved the previous task without `in`, use `in`.
-- If you used `in`, solve it with `or` conditions.
-- Expected: 34 Rows, 3 columns
select 
 last_name, 
 address,
 city
 from customer
 where city = 'Toronto' or city = 'Brampton' or city = 'Mississauga';
-- Find customers who don't live in postal_codes: M3H, K7R, L2V, K7S, or J6A
-- Expected: 874 Rows
select 
 postal_code
 from customer
 where postal_code not in ('M3H', 'K7R', 'L2V', 'K7S', 'J6A');
 
-- Find customer whose last name starts with 'M'.
-- Expected: 76 Rows
select *
from customer
where last_name like "M%" ;
-- Find customers with a `(952)` phone area code.
-- Expected: 5 Rows.
select *
from customer
where phone like "%(952)%" ;
-- Find customers with a '.com' email_address
-- Expected: 599 Rows.
select *
from customer
where email_address like "%.com" ;
-- Which customers don't have a phone number?
-- Expected: 68 Rows.
select *
from customer
where phone is null;

-- Which employees don't have an end_date?
-- In other words, end_date has the null value.
-- Expected: 31 Rows.
select *
from employee
where end_date is null ;
-- Find all projects with `Gabion` in the description.
-- Expected: 179 Rows.
select *
from project
where description like "%Gabion%" ;
-- Select all columns from project_item.
-- Sort them by quantity desc and then project_id desc.
-- What is the largest quantity?
-- What is the largest quantity's project_id and item_id?
-- Expected: project_id 1094, item_id 2, quantity 132.00
select *
from project_item
order by quantity desc, project_id desc;



-- Select a calculated field, full_name, from customer by
-- concatenating the customer's first_name and last_name
-- where the city equals 'Rayside-Balfour'.
-- Expected 9 Rows, 1 column.
select 
    concat(first_name, " ", last_name) as full_name
from customer
where city = 'Rayside-Balfour';
-- Select item_id, name, and price_per_unit for items
-- that are measured by cubic yards (unit_id = 2)
-- and have a price_per_unit greater than $50.
-- Expected: 8 Rows, 3 columns
select * from item;
select
    item_id,
    name,
    price_per_unit
from item
where unit_id = 2 and price_per_unit > 50;
-- The next two tasks require multiple queries to solve.
-- Use the results of one query to write the next query.
-- ==================================================

-- Decide if the customer Mina Ellett has a login record.
-- Step 1: select the customer_id from customer for Mina Ellett.
-- Expected: 1 Row
select * from customer;
select
customer_id
from customer
where first_name = 'Mina' and last_name = 'Ellett';


-- Step 2: use the customer_id to find a record in the login table.
-- Expected: 0 Rows (no login) -- 518 customer id
select * from login;
select *
from login
where customer_id = 518;

-- Which employees work on projects for customer Tadeo Divine?
-- Step 1: find Tadeo Divine's customer_id 854
-- Expected: 1 Row

select
customer_id
from customer
where first_name = 'Tadeo' and last_name = 'Divine';

-- Step 2: use their customer_id to find their records in the project table.
-- Expected: 1 Row project _id 182
select *
from project
where customer_id = 854;

-- Step 3: use the project_id from project to find records in project_employee
-- Expected: 4 Rows
select * 
from project_employee
where project_id = 182;

-- Step 4: use the employee_ids from project_employee to find records in employee
-- Expected: 4 Rows
select * 
from employee
where employee_id = 9 or employee_id = 15 or employee_id =  22 or employee_id =  23; 
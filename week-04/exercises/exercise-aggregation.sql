use gravel_family;

-- Solve each task by writing a query below the task description.
-- Each task describes the expected result.
-- Unfortunately, tasks must be verified manually. :(

-- Example: 
-- Count the number of customer in Toronto
-- Expected: 14

select count(*)
from customer
where city = 'Toronto';

-- How many employees have the last_name 'Soyle'?
-- Expected: 12
select count(*)
from employee
where last_name = 'Soyle';



-- How many projects are there in the database?
-- Expected: 1121
select count(*) as total_projects
from project;

-- What's the earliest project start_date?
-- Expected: 2017-09-23
select min(start_date)
from project;

-- What's the latest employee start_date?
-- Expected: 2020-08-25
select max(start_date)
from project;
-- Count customers per city.
-- Expected: 88 Rows
select count(*),
customer.city
from customer
group by city;
-- Count customers per postal_code.
-- Expected: 84 Rows
select count(*),
customer.postal_code
from customer
group by postal_code;
-- Count employees per last_name.
-- Expected: 3 Rows
select count(*),
employee.last_name
from employee
group by last_name;
-- Count the number of projects per city.
-- Expected: 88 Rows
select count(*),
customer.city
from project p
left outer join customer on customer.customer_id = p.customer_id
group by customer.city;

-- Count the number of projects per city.
-- Sort by the count descending and select the top 10 rows.
-- Expected: 10 Rows
select count(*),
customer.city
from project p
left outer join customer on customer.customer_id = p.customer_id
group by customer.city
order by count(*) desc
limit 10;
-- Which postal_code has the most projects?
-- Expected: M3H
select count(*),
customer.postal_code
from project p
left outer join customer on customer.customer_id = p.customer_id
group by customer.postal_code
order by count(*) desc
limit 10;
-- Count the number of projects per start_date year.
-- Expected: 4 Rows

select count(*),
project.start_date
from project -- left outer join customer on customer.customer_id = p.customer_id
group by project.start_date;



-- Count the number of employees per project in the M3H postal_code.
-- Group by project_id, sort by count descending.
-- Expected: 39 Rows
select count(*),
customer.postal_code,
p.project_id
from project p
join customer on customer.customer_id = p.customer_id
where customer.postal_code = "M3H"
group by p.project_id
order by count(*) desc;

-- Calculate the total cost per project in the 'M3H' postal_code.
-- (Hint: sum a calculation)
-- Expected: 39 Rows
select count(*) project_ID,
sum(item.price_per_unit * pi.quantity) total_cost_per_project
from customer c
join project p on c.customer_id = p.customer_id
join project_item pi on p.project_id = pi.project_id
join item on pi.item_id = item.item_id
where c.postal_code = "M3H"
group by p.project_id;


-- What's the most expensive project in the 'M3H' postal_code?
-- Expected: 18828.00

select count(*) as project_ID,
sum(item.price_per_unit * pi.quantity) total_cost_per_project
from customer c
join project p on c.customer_id = p.customer_id
join project_item pi on p.project_id = pi.project_id
join item on pi.item_id = item.item_id
where c.postal_code = "M3H"
group by p.project_id
order by total_cost_per_project desc;


-- How many projects did each employee work on?
-- Expected: 33 Rows
SELECT count(*), employee.employee_id,
concat( employee.first_name, ' ', employee.last_name) as employee_name
from employee
join project_employee on employee.employee_id = project_employee.employee_id
join project on project_employee.project_id = project.project_id
GROUP BY employee.employee_id;

-- How many employees worked on more than 140 projects?
-- Expected: 10 Rows
SELECT count(*)
from employee
join project_employee on employee.employee_id = project_employee.employee_id
join project on project_employee.project_id = project.project_id
GROUP BY employee.employee_id
HAVING count(*) > 140;

-- How many projects cost more than $20,000?
-- Expected: 55 Rows

select count(*),
sum(item.price_per_unit * pi.quantity) total_cost_per_project
from customer c
join project p on c.customer_id = p.customer_id
join project_item pi on p.project_id = pi.project_id
join item on pi.item_id = item.item_id
group by p.project_id
HAVING total_cost_per_project > 20000;

-- Across all projects, what are the total costs per item?
-- Select the item name and sum.
-- Sort by the sum desc;
-- Expected: 18 Rows
select item.name,
sum(item.price_per_unit * pi.quantity) total_cost_per_item
from project_item pi
join item on pi.item_id = item.item_id
group by item.name
order by total_cost_per_item desc;

-- Across all projects, what are the total costs per item category?
-- Select the category name and sum.
-- Sort by the sum desc;
-- Expected: 7 Rows
SELECT category.name,
sum(item.price_per_unit * pi.quantity) total_cost_per_item_category
from project_item pi
join item on pi.item_id = item.item_id
join category on category.category_id = item.category_id
group by category.name
ORDER BY total_cost_per_item_category desc;
-- What's the average 'Standard Labor' cost per city?
-- Expected: 88 Rows
select
avg(item.price_per_unit * pi.quantity) average_standard_labor_cost
from customer c
join project p on c.customer_id = p.customer_id
join project_item pi on p.project_id = pi.project_id
join item on pi.item_id = item.item_id
where item.name = "Standard Labor"
group by c.city;


-- Challenge: Which customer has the first project of 2019?
-- (Requires a subquery.)
-- Expected: Starkie 2019-01-01select count(*), project.start_date from project -- left outer join customer on customer.customer_id = p.customer_id group by project.start_date LIMIT 0, 1000

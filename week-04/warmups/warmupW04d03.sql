use gravel_family;


-- 1. For Tadeo Divine's projects, find all items that were used, along with their quantity and unit.
-- Try to create a calculated column called item_description that is formatted like: "66.000 Hours of Standard Labor"
-- Expected output: 4 rows

select *,
concat(customer.first_name, " " ,customer.last_name) full_name,
concat(project_item.quantity, ' ', unit.name, "s of ", item.name) item_description
from customer
join project on customer.customer_id = project.customer_id
join project_item on project_item.project_id = project.project_id
 join item on item.item_id = project_item.item_id
 join  unit on unit.unit_id = item.unit_id
where customer.first_name = 'Tadeo' and customer.last_name = 'Divine';

-- 2. How many projects has each employee worked on? Only show employees who have worked on at least 130 projects.
-- Show the employee's first and last names as employee_name, and their total projects as project_count.
-- Expected output: 21 rows

select count(*) as project_count,
concat(e.first_name, " " , e.last_name) employee_name
-- sum(project.project_id) project_count
from employee e
join project_employee on e.employee_id = project_employee.employee_id
join project on  project.project_id = project_employee.project_id
group by e.employee_id;

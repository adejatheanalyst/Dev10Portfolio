 use airbnb_nyc;
 
 select 
 name,         
 host_name 
 from listing; 
 -- select all
 select * from listing;
 -- where clause
 select
 name,
 neighbourhood,
 host_name
 from listing
 where host_name = 'Andrea';
 -- and or
 select
 name, 
 neighbourhood,
 host_name,
 availability_365
 from listing
 where host_name = 'Andrea'
	or availability_365 = 0;
    
    -- varchar, char, text
select * from listing where neighbourhood = 'Astoria';
select * from listing where neighbourhood = 'astoria'; -- not case sensitive
select * from listing where neighbourhood < 'M';
select * from listing where neighbourhood <= 'M';
select * from listing where neighbourhood > 'M';
select * from listing where neighbourhood >= 'M';

-- numbers (int, decimal...)
select  * from listing where price = 100.0;
select  * from listing where price < 100.0;
select  * from listing where price <= 100.0;
select  * from listing where price > 100.0;
select  * from listing where price >= 100.0;

-- dates and times
select * from listing where last_review = '2019-02-15'; -- string literal is converted to a date for comparison
select * from listing where last_review < '2019-02-15';
select * from listing where last_review <= '2019-02-15';
select * from listing where last_review > '2019-02-15';
select * from listing where last_review >= '2019-02-15';
-- where
select *
from listing
where ((neighbourhood_group = 'Manhattan' and price < 100)
    or neighbourhood = 'City Island')
and availability_365 > 0;

select *
from listing
where neighbourhood_group = 'Manhattan'
and reviews_per_month is null;


select *
from listing
where neighbourhood = 'Williamsburg'
and (reviews_per_month is null
    or reviews_per_month = 0);
    
    -- like 
    select
    name,
    host_name
from listing
where name like '%clean';

select
    name,
    host_name
from listing
where host_name like '_o__';

-- IN, NOT IN, BETWEEN
select *
from listing
where neighbourhood in ('Bayside', 'Eltingville', 'Jackson Heights', 'Van Nest');

   select *
from listing
where neighbourhood_group not in ('Manhattan', 'Bronx', 'Brooklyn');
 
    
    select *
from listing
where reviews_per_month in (5, 6, 7, 8);

    select *
from listing
where reviews_per_month between 5 and 8;



select *
from listing
where last_review between '2018-10-01' and '2019-02-01';
select *
from listing
where price between 155.0 and 255.0;

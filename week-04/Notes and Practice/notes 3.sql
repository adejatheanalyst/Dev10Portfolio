-- sorting
select
    name,
    host_name
from listing
order by host_name asc;

select
    name,
    host_name
from listing
order by host_name desc;


select
    name,
    neighbourhood_group,
    neighbourhood,
    host_name
from listing
where price > 200
order by neighbourhood_group asc, neighbourhood asc;



select
    name,
    neighbourhood_group,
    neighbourhood,
    host_name
from listing
order by neighbourhood_group asc, neighbourhood asc, price desc;



select 
    name,
    host_name
from listing
where neighbourhood = 'Hell''s Kitchen'
order by price desc;

select
    name,
    neighbourhood,
    price
from listing
order by price asc
limit 7;

select
    name,
    neighbourhood,
    price
from listing
order by price desc
limit 7;



select
    name,
    neighbourhood,
    price
from listing
order by price desc
limit 10, 7; -- 10 is offset, 7 is count

select * from listing limit 0;        -- zero rows returned
select * from listing limit 5000, 10; -- no rows available after row 4849

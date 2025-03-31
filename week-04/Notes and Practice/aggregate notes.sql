use airbnb_nyc;

select count(listing_id) -- counts non-null listing_ids
from listing; -- 4850

select count(last_review)
from listing;

select count(*)
from listing;



-- AVERAGE
select avg(price)
from listing
where neighbourhood_group = 'Manhattan'
and price > 0;

select 
    avg(price * minimum_nights) avg_min_price
from listing
where neighbourhood = 'Harlem'
and price > 0;

select 
    min(last_review)
from listing
where neighbourhood = 'Williamsburg';


select 
    count(listing_id) listing_count,
    min(price) min_price,
    max(price) max_price,
    avg(price) avg_price,
    std(price) price_std,
    sum(reviews_per_month) total_reviews
from listing
where neighbourhood = 'Williamsburg';


-- GROUP BY

select
    neighbourhood,
    avg(price) avg_price
from listing
group by neighbourhood;



select
    neighbourhood,
    room_type,
    avg(price) avg_price
from listing
group by neighbourhood, room_type
order by neighbourhood, room_type;


select
    neighbourhood,
    room_type,
    min(price) min_price,
    max(price) max_price,
    count(listing_id) listing_count,
    avg(price) avg_price
from listing
group by neighbourhood, room_type
order by avg(price) asc;

-- HAVING

select
    neighbourhood,
    room_type,
    min(price) min_price,
    max(price) max_price,
    count(listing_id) listing_count,
    avg(price) avg_price
from listing
group by neighbourhood, room_type
having avg(price) between 45.00 and 65.00
order by avg(price) asc;
select
    neighbourhood_group,
    neighbourhood,
    room_type,
    min(price) min_price,                              
    max(price) max_price,
    count(listing_id) listing_count,
    avg(price) avg_price
from listing                                           -- 1 Determine which tables are data sources. The SQL engine must know the data source before it can make good decisions about a query strategy. 
													   -- Join clauses are included in this step.
where availability_365 > 0                             -- 2 Filter rows.
    and price > 0
group by neighbourhood_group, neighbourhood, room_type -- 3 Group and aggregate. 
having avg(price) between 45.00 and 65.00              -- 4 Filter aggregate values.
    and count(listing_id) > 1
order by avg(price) asc                                -- 5 Sort the results.
limit 5;                                               -- 6 Limit the rows returned.


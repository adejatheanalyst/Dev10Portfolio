select
    name,
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing;


select
    name,  
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing
where number_of_reviews / 25.0 
        * calculated_host_listings_count > 5.0
order by price * minimum_nights desc;



select
    name,
    host_name
from listing
where number_of_reviews / 25.0 
        * calculated_host_listings_count > 5.0
order by price * minimum_nights desc;
-- CONCAT

select
    name,
    concat(neighbourhood_group, ": ", neighbourhood)
from listing;
-- YEAR
select
    name,
    host_name,
    price,
    year(last_review)
from listing
where year(last_review) + 1 = year(now());


select
    name
from listing
where ifnull(reviews_per_month, 0) = 0; -- null values are replaced with 0



-- If reviews_per_month is 0, make it null.
-- Then exclude all null values.
select
    name,
    reviews_per_month
from listing
where nullif(reviews_per_month, 0) is not null;

select
    name,  
    price * minimum_nights as 'min_price',
    number_of_reviews / 25.0 
        * calculated_host_listings_count as 'credibility'
from listing;
select
    name,  
    price * minimum_nights min_price, -- as and quotes not required
    number_of_reviews / 25.0 
        * calculated_host_listings_count credibility
from listing;

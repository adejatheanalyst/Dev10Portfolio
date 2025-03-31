use dwmh_test;

use dwmh;

select * from reservation;
select 
sum(datediff(day, r.start_date + r.end_date)) as res_dates,
sum(l.standard_rate * datediff(day, r.start_date + r.end_date)) as total_cost
from reservation r
inner join location l on r.location_id = l.location_id;
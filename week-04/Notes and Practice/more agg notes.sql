use bowls;

select
    c.customer_id,
    concat(c.first_name, ' ', c.last_name) customer_name,
    count(o.order_id) order_count
from customer c
inner join `order` o on c.customer_id = o.customer_id
where o.order_status_id != 9
group by c.customer_id
order by count(o.order_id) desc;

select
    concat(c.first_name, ' ', c.last_name) customer_name,
    o.order_id,
    o.order_date,
    sum(oi.quantity * i.price) order_total
from customer c
inner join `order` o on c.customer_id = o.customer_id
inner join order_item oi on o.order_id = oi.order_id
inner join item i on oi.item_id = i.item_id
where c.customer_id in ( -- customer_ids from previous result
    '8ad51114-50ab-4a36-9ee9-fc3f01ec74cc',
    '80bd6ffa-ae27-4d06-8017-16b835e2d3b1',
    '6a1d0276-8eba-4e55-ad30-453091e835fb',
    '1514f6a9-b426-4bb7-bd98-4ab6e96608a3',
    'e6b763b2-2e30-409a-91c8-720d0a68bb29',
    'b25f562a-e547-4b59-a3ef-b7c18f249b92'
)
and o.order_status_id != 9
group by c.customer_id, o.order_id
order by customer_name, o.order_date; -- can sort with an alias


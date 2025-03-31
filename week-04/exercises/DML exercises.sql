use dont_wreck_my_house;

select * from guests;
insert into guests
    (first_name, last_name, email, phone_number) 
    values
   ('Marsiella', 'Joyes', 'mjoyes0@reference.com',"919-101-8488"),
    ('Sheppard', 'Scarff', 'sscarff1@columbia.edu',"847-330-5167"),	
    ('Kiele', 'Hannaway', 'khannaway2@friendfeed.com','965-688-6515'),
    ('Marion',	'Cello'	,'mcello4@php.net','841-821-7876'),
    ('Farlay',	'Mugleston','fmugleston6@1und1.de','(200) 3631673'),
    ('Christina',	'Radband',	'cradband7@deliciousdays.com','(260) 2527265'),
    ('Esteban','Debell', 'edebell8@craigslist.org',	'(589) 3976287');	

    INSERT INTO host(first_name, last_name, email, phone_number 
    ) values
    ('Fatima',	'Hassan',	'fhassan@reuters.com','(706) 3701776'),
    ('Dalston',	'Froggatt',	'dfroggatt5@mozilla.com','(610) 2552867'),
    ('Christina',	'Radband',	'cradband7@deliciousdays.com','(260)2527265'),
    ('Joey',	'Grimsey',	'jgrimsey9@digg.com','(937) 6027503');
    SELECT * FROM host;



INSERT into location
(host_id, address, city, state, postal_code, standard_rate, weekend_rate) values
((select host_id from host where first_name = 'Fatima'),'8 Towne Crossing','Las Vegas','NV', ' 89110',	176,188),	
((select host_id from host where first_name = 'Dalston'),'0879 Laurel Road',"San Francisco"	,'CA', '94121',	89,108),	
((select host_id from host where first_name = 'Christina'),'1690 Little Fleur',"Court Memphis",'TN', '38104',75,113),	
((select host_id from host where first_name = 'Joey'),'8783 Pearson Lane','San Luis Obispo','CA', '93407',200, 226),	
((select host_id from host where first_name = 'Fatima'),'5 John Wall Trail',"New Orleans",	'LA','70149',236,284);
SELECT* FROM location;

INSERT INTO reservation
(guest_id, location_id, start_date, end_date, total_cost) values
((select guest_id from guests where first_name = 'Marsiella'),(select location_id from location where address = '8 Towne Crossing'),'2023-03-01','2023-03-05', 352),
((select guest_id from guests where first_name = 'Sheppard'),(select location_id from location where address = '0879 Laurel Road'),'2023-03-01','2023-03-03', 197),
((select guest_id from guests where first_name = 'Kiele'),(select location_id from location where address = '1690 Little Fleur'),'2023-03-01','2023-03-03', 188),
((select guest_id from guests where first_name = 'Marion'),(select location_id from location where address = '8783 Pearson Lane'),'2023-03-01','2023-03-10', 426),
((select guest_id from guests where first_name = 'Farlay'),(select location_id from location where address = '5 John Wall Trail'),'2023-03-01','2023-03-07', 520),
((select guest_id from guests where first_name = 'Christina'),(select location_id from location where address = '8 Towne Crossing'),'2023-11-15','2023-11-17', 352),
((select guest_id from guests where first_name = 'Esteban'),(select location_id from location where address = '0879 Laurel Road'),'2023-11-15','2023-11-16', 197),
((select guest_id from guests where first_name = 'Marsiella'),(select location_id from location where address = '1690 Little Fleur'),'2023-11-15','2023-11-17', 188),
((select guest_id from guests where first_name = 'Sheppard'),(select location_id from location where address = '8783 Pearson Lane'),'2023-11-15','2023-11-17', 426),
((select guest_id from guests where first_name = 'Kiele'),(select location_id from location where address = '5 John Wall Trail'),'2023-11-15','2023-11-17', 520);
SELECT * FROM reservation;

update host SET
email = "jgrimsey@protonmail.org"
where host_id = 4;


update location SET
address = "105 John Wall TRL"
where location_id = 5;


update reservation SET
end_date = "2023-03-05"
where start_date = '2023-03-01';
set sql_safe_updates = 0;

delete from guests
where guest_id= (select guest_id  where first_name = 'Kiele');

delete from host
where host_id = 4



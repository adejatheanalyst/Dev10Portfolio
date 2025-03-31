use crowd_funding;

insert into campaign (`name`, `description`, start_date, end_date, funding_goal)
	values
    ('Coloma: New Prospects (Expansion and Reprint)', 
    'Saddle up and prepare to strike gold with a reprint and expansion to one of 2019''s hottest titles, Coloma!', 
    '2023-07-01', '2023-08-01', 30000);
    
insert into backer (`name`, email_address) values
	('Lyndell Long', 'llong0@purevolume.com'),
    ('Boothe Blasgen', 'bblasgen1@spiegel.de');
    
insert into pledge(campaign_id, backer_id, amount) values
	(1, 1, 100);
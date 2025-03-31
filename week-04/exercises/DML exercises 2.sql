use field_agent;

insert into agency (short_name, long_name)
    values ('NSA', 'National Security Agency');
    SELECT* FROM agency;


insert into agency (short_name, long_name)
    values ('ACME', 'Agency to Classify & Monitor Evildoers');

    insert into agency (agency_id, short_name, long_name)
    values (50, 'GAS', 'Galactic Alliance Security');

    insert into agency (short_name, long_name)
    values ('MASK', 'Mobile Armored Strike Kommand');


insert into agent 
    (first_name, middle_name, last_name, dob, height_in_inches) 
    values
    ('Hazel','C','Sauven','1954-09-16',76),
    ('Claudian','C','O''Lynn','1956-11-09',41),
    ('Winn','V','Puckrin','1999-10-21',60),
    ('Kiab','U','Whitham','1960-07-29',52),
    ('Min','E','Dearle','1967-04-18',44),
    ('Urban','H','Carwithen','1996-12-22',58),
    ('Ulises','B','Muhammad','2008-04-01',80),
    ('Phylys','Y','Howitt','1979-03-28',68);
 SELECT * FROM agent

-- do not do this
select * from security_clearance;
insert into security_clearance values
    (1, 'Top Secret');

insert into agency_agent 
    (agency_id, agent_id, identifier, security_clearance_id, activation_date)
    select
        agency.agency_id,                              -- agency_id
        agent.agent_id,                                -- agent_id
        concat(agency.agency_id, '-', agent.agent_id), -- identifier
        1,                                             -- security_clearance_id
        date_add(agent.dob, interval 10 year)          -- activation_date
    from agency
    inner join agent;


     SELECT * FROM agency;
     SELECT * from agent;

 update agency set
    long_name = 'Nascent Science Agency'
where agency_id = 1;

update agent set
    middle_name = 'K',
    dob = '2002-04-09'
where agent_id = 7;

-- update [table name] set
   -- [column1] = value1,
   -- [column2] = value2,
    --[columnN] = valueN
-- where [condition];

update agency_agent set
    identifier = '003',
    activation_date = '2012-9-19',
    is_active = false
where agency_id = 1
and agent_id = 1;

SELECT * FROM agency_agent;


update agent set
    first_name = 'Kilo',
    last_name = 'Connect'
where agent_id = 1024;

-- 0 row(s) affected Rows matched: 0  Changed: 0  Warnings: 0

-- disable safe updates
set sql_safe_updates = 0;

-- change middle names to lower case, add an inch
-- to agents who were born before 1999-06-15
update agent set
    middle_name = lower(middle_name),
    height_in_inches = height_in_inches + 1
where dob < '1999-06-15';

-- change all agent middle_names to 'X'
update agent set
    middle_name = 'X';

-- enable safe updates
set sql_safe_updates = 1;
SELECT * FROM agent;


-- DELETE delete from [table name] where [condition];
-- remove all agency affiliations for Claudian O'Lynn
-- one at a time
delete from agency_agent 
where agency_id = 1 and agent_id = 2;

delete from agency_agent 
where agency_id = 2 and agent_id = 2;

delete from agency_agent 
where agency_id = 50 and agent_id = 2;

delete from agency_agent 
where agency_id = 51 and agent_id = 2;

-- delete Claudian O'Lynn
delete from agent where agent_id = 2;



-- disable safe updates
set sql_safe_updates = 0;

-- remove all agency affiliations for Winn Puckrin
-- in one statement.
delete from agency_agent where agent_id = 3;

-- delete Winn Puckrin
delete from agent where agent_id = 3;

-- enable safe updates
set sql_safe_updates = 1;

-- CHANGE COLUMN NAME
update agent set
    first_name = first_name,
    middle_name = middle_name,
    last_name = last_name,
    dob = dob
where agent_id = 1;
SELECT * FROM agent;

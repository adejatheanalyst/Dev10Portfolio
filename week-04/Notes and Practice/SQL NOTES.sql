-- Single-line comments start with two dashes.

/* Multi-line
comment.
*/
-- use airbnb_nyc;                -- Statements end with a semicolon.

select
 name,         -- String literals are delimited with a single quote.
 host_name 
 from listing; -- Double quotes work as well.

                  -- Keywords are sometimes capitalized to make them
                               -- stand out, but it's not necessary.
                               -- By default, most of SQL is case insensitive.
                               -- It can be made case-sensitive through configuration.

select 1.1 + 2.2;              -- SQL has operators like '+'.

insert into listing values     -- For the most part, whitespace doesn't matter.
    (204,                      -- Statements can be broken up.
    'Skylit Midtown Castle',2845,
    'Jennifer','Manhattan','Midtown',
    40.75362,                  -- Numeric literals are numbers without quotes.
    -73.98377,
    'Entire home/apt',225,1,45,
    '2019-05-21',              -- Date literals are strings that are automatically converted to a date.
    0.38,2,355);               -- Parentheses group things.


drop database if exists restaurant_dishes;
CREATE DATABASE restaurant_dishes;
use restaurant_dishes;
CREATE TABLE dishes (
    dish_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT
);
INSERT INTO dishes
    (name, price, description) values
    ("Spaghetti Bolognese", 19.99, "Spaghetti Bolognese is a pasta dish made with a meat sauce."),
    ("Chicken Alfred", 22.12, "Chicken Alfredo is a pasta dish made from fettuccine tossed with Parmesan cheese and butter."),
    ("Spaghetti Carbonara", 18.99, "Spaghetti Carbonara is a pasta dish made with eggs, cheese, bacon, and black pepper."),
    ("Chicken Parmesan", 24.99, "Chicken Parmesan is a dish made from breaded chicken breast covered in tomato sauce and cheese."),
    ("Sesame chicken", 23.88, "Sesame chicken is a dish made from battered chicken pieces tossed in a sweet and savory sauce."),
    ("Chicken Marsala", 25.99, "Chicken Marsala is a dish made from chicken cutlets cooked in a Marsala wine sauce.");
  select * from dishes;
CREATE TABLE menu (
    menu_id INT PRIMARY KEY AUTO_INCREMENT unique,
    dish_id INT NOT NULL,
    special_dish BIT NOT NULL DEFAULT 0,
    menu_item_name VARCHAR(255) NOT NULL,
    `date` DATE NOT NULL,
    constraint fk_menu_dish_id
        foreign key (dish_id)
        references dishes(dish_id));
select * from menu;
alter table menu;
   -- Create today's menu, which includes a subset of all the available dishes
    -- Take dishes on/off the menu
    --List dishes on the menu today
    -- Mark some dishes as specials
INSERT INTO menu
(dish_id, date, special_dish, menu_item_name) values
((select dish_id from dishes where name = "Spaghetti Bolognese"), "2023-03-01", 1, "Spaghetti Bolognese"),
((select dish_id from dishes where name = "Chicken Alfred"), "2023-03-01", 0, "Chicken Alfred"),
((select dish_id from dishes where name = "Spaghetti Carbonara"), "2023-03-01", 1, "Spaghetti Carbonara" ),
((select dish_id from dishes where name = "Chicken Parmesan"), "2023-03-01", 0, "Chicken Parmesan"),
((select dish_id from dishes where name = "Sesame chicken"), "2023-03-01", 0, "Sesame chicken"),
((select dish_id from dishes where name = "Chicken Marsala"), "2023-03-01", 0, "Chicken Marsala");



--3 --Create tomorrow's menu
    --- Don't lose track of today's menu
     --Same asks as today's menu
    --- List dishes on the menu for a given day
     --Specials can change day-to-day
    --Should be able to list which dishes were on special on a previous day
 
INSERT INTO menu
(dish_id, `date`, special_dish, menu_item_name) values
((select dish_id from dishes where name = "Spaghetti Bolognese"), "2023-03-02", 0, "Spaghetti Bolognese"),
((select dish_id from dishes where name = "Chicken Alfred"), "2023-03-02", 1, "Chicken Alfred"),
((select dish_id from dishes where name = "Spaghetti Carbonara"), "2023-03-02", 0, "Spaghetti Carbonara" ),
((select dish_id from dishes where name = "Chicken Parmesan"), "2023-03-02", 0, "Chicken Parmesan"),
((select dish_id from dishes where name = "Sesame chicken"), "2023-03-02", 1, "Sesame chicken"),
((select dish_id from dishes where name = "Chicken Marsala"), "2023-03-02", 1, "Chicken Marsala");
 
select * from menu where date = "2023-03-01" and special_dish = 1


select * from menu;

   -- 4 A day can have multiple menus, for example lunch vs dinner, or prix fixe vs a la carte
   -- - Create multiple menus for a given day
    -- A dish might have a different description on one menu vs another
    -- Identify which menu is which for a given day
    -- List all past menus
    -- List all the menus for a given d

    
    
 

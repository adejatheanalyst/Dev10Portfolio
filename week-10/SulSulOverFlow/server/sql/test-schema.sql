drop database if exists sulsul_test;
create database sulsul_test;
use sulsul_test;



create table users (
  user_id int primary key auto_increment,
  username varchar(50) unique,
  `password` varchar (50)
);


create table questions (
  question_id int primary key auto_increment,
  title varchar(255) not null,
  body text not null,
  user_id integer not null,
  foreign key (user_id) references users(user_id)
);


create table answers (
  answer_id int primary key auto_increment,
  body varchar(255) not null unique,
  user_id integer not null,
  question_id integer not null,
  foreign key (user_id) references users(user_id),
  constraint fk_question_id
  foreign key (question_id) references questions(question_id)
  on delete cascade
);

delimiter //
create procedure set_known_good_state()
begin

    delete from answers;
    alter table answers auto_increment = 1;
    delete from questions;
    alter table questions auto_increment = 1;
    delete from users;
    alter table users auto_increment = 1;
    
    insert into users (username, `password`) values ('alice', 'password1');
    insert into users (username, `password`) values ('bob', 'password2');
    insert into users (username, `password`) values ('charlie', 'password3');
    
    insert into questions (title, body, user_id) values ('What does SulSul even mean?', 'I want to know what sul sul means. Ive been playing sims for years', 1);
    insert into questions (title, body, user_id) values ('What is the airspeed velocity of an unladen swallow?', 'I am curious.', 2);
    insert into questions (title, body, user_id) values ('What is the capital of Assyria?', 'I am curious.', 3);
    
    insert into answers (body, user_id, question_id) values ('42', 1, 1);
    insert into answers (body, user_id, question_id) values ('What do you get if you multiply six by nine?', 2, 1);
    insert into answers (body, user_id, question_id) values ('What do you mean? An African or European swallow?', 3, 2);
    insert into answers (body, user_id, question_id) values ('I don''t know that.', 1, 3);
    
    end//
delimiter ;

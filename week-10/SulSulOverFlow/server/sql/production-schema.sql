drop database if exists sulsul;
create database sulsul;
use sulsul;



create table users (
  user_id int primary key auto_increment,
  username varchar(50) unique,
  `password` varchar (50)
);


create table questions (
  question_id int primary key auto_increment,
  title varchar(255) not null,
  body varchar(255) not null,
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

  insert into users (username, `password`) values ('test1', 'password1');
  insert into questions (title, body, user_id) values ('What does SulSul even mean?', 'I want to know what sul sul means. Ive been playing sims for years', 1);
  insert into answers (body, user_id, question_id) values ('42', 1, 1);
  
  select * from users;
  select * from questions;
  select * from answers;



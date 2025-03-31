drop database if exists moodvice_test;
create database moodvice_test;
use moodvice_test;
create table moodType (
moodId int primary key auto_increment,
moodType enum ('Happy', 'Sad', 'Angry', 'Anxious')
);
create table resources(
resourceId int primary key auto_increment,
title varchar(255) not null unique,
resourceUrl text,
moodId int,
foreign key (moodId) references moodType(moodId)
);

create table users (
  userId int primary key auto_increment,
  username varchar(50) unique,
  email varchar(255) unique,
  `password` varchar (50),
  created_at date default (CURDATE())
);

create table userMoods(
  userMoodId int primary key auto_increment,
  userId int not null,
  moodId int not null,
  mood_notes varchar(255),
  created_at date default (CURDATE()),
  constraint fk_user_id
  foreign key (userId) references users(userId)
  on update cascade
  on delete cascade,
  foreign key (moodId) references moodType(moodId)
);

create table moodVice(
moodViceId int primary key auto_increment,
title varchar(255) not null,
body varchar(255) not null unique,
userId int not null,
moodId int,
created_at date default (CURDATE()),
constraint fk_user_id2
foreign key (userId) references users(userId)
on update cascade
on delete cascade,
foreign key (moodId) references moodType(moodId)
);
create table moodViceReply(
replyId int primary key auto_increment,
title varchar(255) not null,
body varchar(255) not null unique,
userId integer not null,
moodId int not null,
moodViceId int not null,
created_at date default (CURDATE()),
constraint fk_user_id3
foreign key (userId) references users(userId)
on update cascade
on delete cascade,
constraint fk_moodVice_id2
foreign key (moodViceId) references moodVice(moodViceId)
on update cascade
on delete cascade,
foreign key (moodId) references moodType(moodId)
);

create table media(
mediaId int primary key auto_increment,
moodId int,
mediaType enum ('video', 'song', 'poem', 'image'),
contentUrl text,
`description` varchar(255),
foreign key (moodId) references moodType(moodId)
);
delimiter //
create procedure set_known_good_state()
begin

	delete from media;
    alter table media auto_increment = 1;
    delete from moodViceReply;
    alter table moodViceReply auto_increment = 1;
    delete from moodVice;
    alter table moodVice auto_increment = 1;
    delete from userMoods;
    alter table userMoods auto_increment = 1;
    delete from users;
    alter table users auto_increment = 1;
    delete from resources;
    alter table resources auto_increment= 1;
    delete from moodType;
    alter table moodType auto_increment = 1;

	insert into moodType(moodType)
		values
		('Happy'),
		('Sad'),
        ('Angry'),
        ('Anxious');
insert into resources(title, resourceUrl, moodId) values
("Suicide Prevention", 'https://988lifeline.org/?utm_source=google&utm_medium=web&utm_campaign=onebox', 2),
("test", 'https://988lifeline.org/?utm_source=google&utm_medium=web&utm_campaign=onebox', 2),
("test2", 'https://988lifeline.org/?utm_source=google&utm_medium=web&utm_campaign=onebox', 2),
("test3", 'https://988lifeline.org/?utm_source=google&utm_medium=web&utm_campaign=onebox', 2);

    insert into users (username, email, `password`)
		values
        ('testUsername', 'testemail2@test.com' , 'testPassword'),
        ('testUsername4', 'testemail1@test.com' , 'testPassword'),
        ('testUsername2', 'testemail7@test.com' , 'testPassword'),
        ('testUsername3', 'testemail9@test.com' , 'testPassword');

insert into userMoods(userId, moodId, mood_notes, created_at) values
    (1, 2, "testnotes for sad mood", '2025-03-10'),
    (1, 3, "testnotes for angry mood", '2025-03-10'),
    (1, 4, "testnotes for Happy mood", '2025-03-10'),
    (1, 2, "testnotes for sad mood", '2025-03-10'),
    (1, 3, "testnotes for angry mood", '2025-03-10'),
    (1, 4, "testnotes for Happy mood", '2025-03-10'),
    (1, 1, "testnotes for anxious mood", '2025-03-10');

insert into moodVice(title, body, userId, moodId) values
    ("testTitle", "testBody", 1, 4),
    ("testTitle", "testBody2", 2, 4),
    ("testTitle", "testBody3", 3, 4),
    ("testTitle", "testBody4", 4, 4);

    insert into moodViceReply(title, body, userId, moodId, moodViceId, created_at) values
    ("testTitle", "testBody2", 1, 4, 1, '2025-03-10'),
    ("testTitle", "testBody3", 1, 4, 1, '2025-03-10'),
    ("testTitle", "testBody4", 3, 2, 1, '2025-03-10'),
    ("testTitle", "testBody5", 2, 3, 1, '2025-03-10'),
    ("testTitle", "testBody6", 2, 1, 1, '2025-03-10');

insert into media(moodId, mediaType, contentUrl, `description`) values
    (2, 'video', 'https://res.cloudinary.com/ddugswahb/raw/upload/v1742410574/watch_uvkfsf', 'videoTest'),
    (1, 'image', 'https://res.cloudinary.com/ddugswahb/image/upload/v1742410650/testimage_v4y4v3.jpg', 'image'),
    (2, 'image', 'https://res.cloudinary.com/ddugswahb/image/upload/v1742410650/testimage_v4y4v3.jpg', 'image');

end//
delimiter ;
drop database if exists moodvice;
create database moodvice;
use moodvice;

create table moodType (
moodId int primary key auto_increment,
moodType enum ('Happy', 'Sad', 'Angry', 'Anxious', 'calm', 'humorous', 'fearful')
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

	insert into moodType(moodType)
		values
		('Happy'),
		('Sad'),
        ('Angry'),
        ('Anxious'),
        ('calm'),
        ('humorous'),
        ('fearful');
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
    (1, 2, "testnotes", '2025-03-10'),
    (2, 3, "testnotes", '2025-03-10'),
    (3, 4, "testnotes", '2025-03-10'),
    (4, 2, "testnotes ", '2025-03-10'),
    (1, 3, "", '2025-03-10'),
    (2, 4, "", '2025-03-10'),
    (3, 5, "", '2025-03-10'),
    (4, 6, "", '2025-03-10'),
    (1, 7, "", '2025-03-10'),
    (2, 1, "", '2025-03-10'),
    (3, 2, "", '2025-03-10'),
    (4, 3, "", '2025-03-10'),
    (1, 4, "", '2025-03-10'),
     (1, 4, "", '2025-03-10'),
    (2, 5, "", '2025-03-10'),
    (3, 6, "", '2025-03-10'),
    (1, 7, "", '2025-03-10'),
    (1, 1, "", '2025-03-10');

insert into moodVice(title, body, userId, moodId) values
    ("Not in a good Mood", "i want to feel better", 1, 4),
    ("Feeling better today", "today i got to get some free time and go outside.", 2, 4),
    ("Need advice on how to cope", "dont know what to do about this situation", 3, 4),
    ("just want to feel safe", "dont know where else to go", 4, 4),
    ("Not in a good Mood", "i want to feel better2", 1, 4),
    ("Feeling better today", "today i got to get some free time and go outside.2", 2, 4),
    ("Need advice on how to cope", "dont know what to do about this situation2", 3, 4),
    ("just want to feel safe", "dont know where else to go2", 4, 4);

    insert into moodViceReply(title, body, userId, moodId, moodViceId, created_at) values
    ("some tips", "i have some different options for you", 1, 4, 1, '2025-03-10'),
    ("you got this...", "im happy to suggest....", 1, 4, 1, '2025-03-10'),
    ("you got this...", "im happy to suggest.....", 3, 2, 1, '2025-03-10'),
    ("you got this...", "im happy to suggest.......", 2, 3, 1, '2025-03-10'),
    ("you got this...", "im happy to suggest........", 2, 1, 1, '2025-03-10');

insert into media(moodId, mediaType, contentUrl, `description`) values
    (1, 'video', 'https://www.youtube.com/embed/xvFZjo5PgG0?si=RiHgWxPGIvwI592X', 'videoTest'),
    (2, 'song', '', 'song'),
    (3, 'poem', "What passing-bells for these who die as cattle?
Only the monstrous anger of the guns.
Only the stuttering rifles' rapid rattle
Can patter out their hasty orisons.
No mockeries now for them; no prayers nor bells;
Nor any voice of mourning save the choirs,
The shrill, demented choirs of wailing shells;
And bugles calling for them from sad shires.
What candles may be held to speed them all?
Not in the hands of boys, but in their eyes
Shall shine the holy glimmers of good-byes.
The pallor of girls' brows shall be their pall;
Their flowers the tenderness of patient minds,
And each slow dusk a drawing-down of blinds.", 'Wilfred Owen'),
    (4, 'image', 'https://www.calmclinic.com/storage/images/249/qozlfy/main/w1600.png', 'image'),
    (5, 'video', 'https://www.youtube.com/embed/xvFZjo5PgG0?si=RiHgWxPGIvwI592X', 'video'),
    (6, 'song', 'https://res.cloudinary.com/ddugswahb/image/upload/v1742410650/testimage_v4y4v3.jpg', 'song'),
    (1, 'poem', "And what is Life? An hour-glass on the run,
A mist retreating from the morning sun,
A busy, bustling, still-repeated dream.
Its length? A minute's pause, a moment's thought.
And Happiness? A bubble on the stream,
That in the act of seizing shrinks to nought.

And what is Hope? The puffing gale of morn,
That of its charms divests the dewy lawn,
And robs each flow'ret of its gem -and dies;
A cobweb, hiding disappointment's thorn,
Which stings more keenly through the thin disguise.

And what is Death? Is still the cause unfound?
That dark mysterious name of horrid sound?
A long and lingering sleep the weary crave.
And Peace? Where can its happiness abound?
Nowhere at all, save heaven and the grave.

Then what is Life? When stripped of its disguise,
A thing to be desired it cannot be;
Since everything that meets our foolish eyes
Gives proof sufficient of its vanity.
'Tis but a trial all must undergo,
To teach unthankful mortals how to prize
That happiness vain man's denied to know,
Until he's called to claim it in the skies.", 'John Clare');


select * from users;
select * from moodVice;
select * from moodViceReply;
select * from moodType;
select * from media;
select * from userMoods;
select * from resources;
select * from userMoods where created_at = "2025-03-25" and userId = 1;
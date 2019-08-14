use converge;

drop table if exists sampleData;
drop table if exists Comments;
drop table if exists Posts;
drop table if exists Users;
drop table if exists Categories;
drop table if exists follows;

create table Users
(
	userID int unsigned not null auto_increment,
	userName varchar(64) not null unique,
	password binary(60) not null,
	fullName varchar(64) not null,
	joined Date not null,
	
	primary key(userID)
);

create table Categories
(
	catID int unsigned not null auto_increment,
	title varchar(64) not null unique,
	description Text,
	followers int unsigned,
	
	primary key(catID)
);

create table follows
(
	userID int unsigned not null,
	catID int unsigned not null,
	
	foreign key(userID) references Users(UserID),
	foreign key(catID) references Categories(catID)
);

create table Posts
(
	postID int unsigned not null auto_increment,
	title TinyText not null,
	body Text,
	link TinyText,
	likes int unsigned,
	dislikes int unsigned,
	shares int unsigned,
	postedOn Date,
	
	userID int unsigned not null,
	catID int unsigned not null,
	
	primary key(postID),
	foreign key(userID) references Users(userID),
	foreign key(catID) references Categories(catID)
);

create table Comments
(
	comID int unsigned not null auto_increment,
	body Text not null,
	likes int unsigned,
	dislikes int unsigned,
	creationDate Date,
	userID int unsigned not null,
	postID int unsigned not null,
	
	primary key(comID),
	foreign key(userID) references Users(userID),
	foreign key (postID) references Posts(postID)
	
);

--SAMPLE DATA FOR TESTING PURPOSES
insert into Categories(title, description)
values('Sports', 'Post anything sports related');

insert into Categories(title, description)
values('Gaming', 'Post anything gaming related');

insert into Categories(title, description)
values('Technology', 'Post anything technology related');

insert into Categories(title, description)
values('Food', 'Post anything food related');

insert into Categories(title, description)
values('Cars', 'Post anything car related');

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Sport title 1', 'Sample Sport Description 1', 'www.google.com', CURDATE(),1,1);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Sport title 2', 'Sample Sport Description 2', 'www.google.com', CURDATE(),1,1);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Sport title 3', 'Sample Sport Description 3', 'www.google.com', CURDATE(),1,1);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Sport title 4', 'Sample Sport Description 4', 'www.google.com', CURDATE(),1,1);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Sport title 5', 'Sample Sport Description 5', 'www.google.com', CURDATE(),1,1);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Gaming title 1', 'Sample Gaming Description 1', 'www.google.com', CURDATE(),1,2);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Gaming title 2', 'Sample Gaming Description 2', 'www.google.com', CURDATE(),1,2);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Gaming title 3', 'Sample Gaming Description 3', 'www.google.com', CURDATE(),1,2);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Gaming title 4', 'Sample Gaming Description 4', 'www.google.com', CURDATE(),1,2);

insert into Posts(title, body, link, postedOn, userID, catID)
values('Sample Gaming title 5', 'Sample Gaming Description 5', 'www.google.com', CURDATE(),1,2);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 1', CURDATE(), 1, 1);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 2', CURDATE(), 1, 1);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 3', CURDATE(), 1, 1);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 4', CURDATE(), 1, 1);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 1', CURDATE(), 1, 2);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 2', CURDATE(), 1, 2);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 3', CURDATE(), 1, 2);

insert into Comments(body, creationDate, userID, postID)
values('Sample sport comment 4', CURDATE(), 1, 2);
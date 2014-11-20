use [Exam-UserRuns]

if OBJECT_ID('Runs','U') is not null
	drop table Runs
	
if OBJECT_ID('Games','U') is not null
	drop table Games

if OBJECT_ID('Users','U') is not null
	drop table Users


go


create table Users
	(userID smallint primary key identity(1,1),
	userName nvarchar(30),
	userEmail nvarchar(30),
	userDevice nvarchar(30),
	userAlias nvarchar(30))


create table Games
	(gameID smallint primary key identity(1,1),
	gameName nvarchar(30),
	gameLevels int,
	gameOS nvarchar(30),
	gamePublisher nvarchar(30))
	
	
create table Runs
	(runID smallint primary key identity(1,1),
	userID smallint references Users(userID),
	gameID smallint references Games(gameID),
	levelNo int,
	overallTime int,
	collectedCoins int)

go

insert Users values ('user1', 'mail1', 'device1', 'alias1')
insert Users values ('user2', 'mail2', 'device2', 'alias2')
insert Users values ('user3', 'mail3', 'device3', 'alias3')

insert Games values ('Subway Surfers', 15, 'Android', 'Gameloft')
insert Games values ('Temple Run', 20, 'IOS', 'Google')
insert Games values ('Temple Run2', 15, 'IOS', 'Stevie')

insert Runs values (1, 1, 4, 20, 400)
insert Runs values (1, 2, 3, 30, 100)
insert Runs values (1, 3, 5, 35, 800)
insert Runs values (2, 1, 8, 65, 600)
insert Runs values (2, 3, 10, 55, 450)

go

select * from Users
select * from Games
select * from Runs
USE [Practice-MovieFestival]

if OBJECT_ID('Schedule', 'U') IS NOT NULL
drop table Schedule

if OBJECT_ID('Cinema', 'U') IS NOT NULL
drop table Cinema

if OBJECT_ID('Movie', 'U') IS NOT NULL
drop table Movie

if OBJECT_ID('Category', 'U') IS NOT NULL
drop table Category

if OBJECT_ID('Producer', 'U') IS NOT NULL
drop table Producer

Create table Category
(CategoryID smallint PRIMARY KEY IDENTITY(1,1),
CategoryName NVARCHAR(30))

Create table Producer
(ProducerID smallint PRIMARY KEY IDENTITY(1,1),
ProducerName NVARCHAR(100))

Create table Movie
(MovieID smallint PRIMARY KEY IDENTITY(1,1),
Title NVARCHAR(30),
CategoryId smallint references Category(CategoryID),
ProducerId smallint references Producer(ProducerID))

Create table Cinema
(CinemaID smallint PRIMARY KEY IDENTITY(1,1),
CinemaName NVARCHAR(30),
Location nvarchar(100),
NoOfSeats smallint)

Create table Schedule
(CinemaId smallint references Cinema(CinemaID),
MovieId smallint references Movie(MovieID),
StartTime datetime,
SoldTickets smallint,
primary key (CinemaID, MovieID, StartTime)
)
go

insert Category values('Comedy')
insert Category values('Fantesy')
insert Category values('Drama')
insert Category values('SF')
insert Category values('Romance')
insert Category values('ZeroLevel')

insert into Producer values('Spilberg')
insert into Producer values('Menskov')
insert into Producer values('Kubrick')
insert into Producer values('Percival')
insert into Producer values('Zemeckis')
insert into Producer values('Jackson')
insert into Producer values('Doensjsdrcf')


Insert Movie values('Movie1', 4, 1)
Insert Movie values('Movie2', 3, 2)
Insert Movie values('Movie3', 4, 1)
Insert Movie values('Movie4', 4, 5)
Insert Movie values('Movie5', 6, 7)
Insert Movie values('Movie6', 2, 6)
Insert Movie values('Movie6', 3, 4)
Insert Movie values('Movie6', 4, 3)
Insert Movie values('Movie6', 2, 6)

GO

Insert Cinema values('Cinema City Iulius', 'Iulius Mall', 1700)
Insert Cinema values('Republica', 'Mihai Viteazul', 800)
Insert Cinema values('Arta', 'Universitatii', 200)

Insert Schedule values(1, 9,'1-1-2014 12:00:00', 1700)
Insert Schedule values(1, 6,'1-1-2014 15:00:00', 1699)
Insert Schedule values(2, 9,'1-1-2014 12:00:00', 800)

Insert Schedule values(1, 7,'1-1-2014 12:00:00', 1700)
Insert Schedule values(1, 7,'1-1-2014 15:00:00', 1700)
Insert Schedule values(1, 7,'1-1-2014 19:00:00', 1699)
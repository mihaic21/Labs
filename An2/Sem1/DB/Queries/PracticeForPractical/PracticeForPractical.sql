create table TrainTypes(
	TypeID int primary key identity(1,1) not null,
	Description nvarchar(50) not null
	)


create table Trains(
	TrainID int primary key identity(1,1) not null,
	Name nvarchar(50) not null,
	TypeID int not null,
	foreign key (TypeID) references TrainTypes(TypeID)
)
	

create table Routes(
	RouteID int primary key identity(1,1) not null,
	name varchar(50),
	TrainID int not null unique,
	foreign key(TrainID) references Trains(TrainID)
	)

create table Stations(
	StationID int primary key identity(1,1) not null,
	name varchar(50)
	)

create table RoutesStations(
	RouteID int,
	StationID int,
	arrivalTime datetime,
	departureTime datetime,
	foreign key (RouteID) references Routes(RouteID),
	foreign key (StationID) references Stations(StationID),
	primary key (RouteID, StationID)
	)
	
drop table RoutesStations
drop table Stations
drop table Routes
drop table Trains
drop table TrainTypes
	
insert into TrainTypes values('type1')
insert into TrainTypes values('type2')
insert into TrainTypes values('type3')

insert into Trains values ('train1', 1)
insert into Trains values ('train2', 2)
insert into Trains values ('train3', 3)

insert into Routes values ('antifoamea',1)
insert into Routes values ('foamea',2)
insert into Routes values ('nefoamea',3)

insert into Stations values ('station1')
insert into Stations values ('station2')
insert into Stations values ('station3')

--insert into RoutesStations values (1, 1, 2014-1-30, 2014-1-30)

--select * from RoutesStations


--PART B - Stored Procedure


create procedure ManageRoutesStations
(
@rname nvarchar(50),
@sname nvarchar(50),
@adate datetime,
@ddate datetime
)
as
	declare @rid smallint, @sid smallint
	set @rid = (select rid from routes where rname = @rname)
	select @sid = (select sid from stations where sname = @sname)
	if exists (select rid, sid from routes_stations where rid = @rid and sid = @sid)
		update routes_stations
		set adate = @adate, ddate = @ddate
		where rid = @rid and sid = @sid
	else
		insert into routes_stations values (@rid, @sid, @adate, @ddate)



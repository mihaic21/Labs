create table Students(
	StudentID int primary key identity(1,1),
	StudentName nvarchar(30) not null,
	GroupName nvarchar(30) not null
	)

create table Tasks(
	TaskID int primary key identity(1,1),
	TaskName nvarchar(30) not null,
	)

create table Grades(
	GradeID int primary key identity(1,1),
	Grade int not null
	)

create table StudentTasks(
	StudentID int not null,
	TaskID int not null,
	GradeID int not null,
	foreign key (StudentID) references Students(StudentID),
	foreign key (TaskID) references Tasks(TaskID),
	foreign key (GradeID) references Grades(GradeID),
	primary key (StudentID, TaskID)
	)

create table Comments(
	CommentID int primary key identity(1,1),
	--GradeID int not null,
	CommentName nvarchar(30) not null,
	CommentStatus nvarchar(30) not null,
	--foreign key (GradeID) references Grades(GradeID)
	)
	
create table CommentsGrades(
	CommentID int not null,
	GradeID int not null,
	foreign key (CommentID) references Comments(CommentID),
	foreign key (GradeID) references Grades(GradeID),
	primary key (CommentID, GradeID)
	)


--Part 2: Stored Procedure

create procedure addGrade(
	@studentID int,
	@taskID int,
	@gradeID int,
	@commentID int
	)
as
begin
	declare @duplicates int
	set @duplicates = 0
	
	select @duplicates = COUNT(*) from StudentTasks where @studentID = StudentID and @taskID = TaskID
	
	if @duplicates = 0
		begin
			insert into StudentTasks values (@studentID, @taskID, @gradeID)
			insert into CommentsGrades values (@commentID, @gradeID)
		end
	else
		insert into CommentsGrades values (@commentID, @gradeID)
end
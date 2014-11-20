USE [Practice-TrackingSystem]

IF OBJECT_ID('Task','U') IS NOT NULL
	DROP TABLE Task
	
IF OBJECT_ID('Developers_Projects','U') IS NOT NULL
	DROP TABLE Developers_Projects

IF OBJECT_ID('Developer','U') IS NOT NULL
	DROP TABLE Developer

IF OBJECT_ID('Project','U') IS NOT NULL
	DROP TABLE Project


GO


CREATE TABLE Project
	(ProjID SMALLINT PRIMARY KEY IDENTITY(1,1),
	ProjName NVARCHAR(100),
	StartDate DATETIME,
	EndDate DATETIME)

CREATE TABLE Developer
	(DevID SMALLINT PRIMARY KEY IDENTITY(1,1),
	DevName NVARCHAR(100))

CREATE TABLE Developers_Projects
	(DevID SMALLINT REFERENCES Developer(DevID),
	ProjID SMALLINT REFERENCES Project(ProjID),
	PRIMARY KEY(DevID, ProjID))

CREATE TABLE Task
	(TaskID SMALLINT PRIMARY KEY IDENTITY(1,1),
	Title NVARCHAR(100),
	TaskDescription NVARCHAR(MAX),
	DevID SMALLINT,
	ProjID SMALLINT,
	TaskType VARCHAR(13) CHECK (TaskType = 'technical' OR TaskType = 'bug' or TaskType = 'improvement'),
	TaskStatus VARCHAR(13) CHECK (TaskStatus = 'started' OR TaskStatus = 'in progress' OR TaskStatus = 'closed'),
	TaskPriority VARCHAR(13) CHECK (TaskPriority = 'critical' OR TaskPriority = 'show-stopper' OR
					TaskPriority = 'minor' OR TaskPriority = 'trivial'),
	FOREIGN KEY (DevID, ProjID) REFERENCES Developers_Projects(DevID, ProjID))
	
GO

INSERT Project VALUES ('P1', '1-1-2010', '1-1-2011')
INSERT Project VALUES ('P2', '1-1-2012', '1-1-2013')
INSERT Project VALUES ('P3', '1-1-2014', '1-1-2015')

INSERT Developer VALUES ('Robert')
INSERT Developer VALUES ('Amalia')
INSERT Developer VALUES ('Alin')

INSERT Developers_Projects VALUES (1, 1)
INSERT Developers_Projects VALUES (1, 2)
INSERT Developers_Projects VALUES (1, 3)
INSERT Developers_Projects VALUES (2, 1)
INSERT Developers_Projects VALUES (2, 2)

INSERT Task VALUES ('FixCriticalBug', 'Fixing critical bug', 1, 1, 'bug', 'started', 'critical')
INSERT Task VALUES ('FixMinorBug', 'Fixing minor bug', 2, 1, 'bug', 'closed', 'minor')
INSERT Task VALUES ('AddButton', 'Add Button :)', 1, 2, 'technical', 'started', 'trivial')
INSERT Task VALUES ('ImproveUX', 'Improving user experience', 1, 2, 'technical', 'closed', 'critical')

SELECT * FROM Developer
SELECT * FROM Project
SELECT * FROM Developers_Projects
SELECT * FROM Task

GO


-- For a given project, display all the developers involved, together with a percent of
-- tasks completion (defined as total number of tasks resolved * 100 / total number of tasks assigned).
--  You must also create an index to be used by this query (you must prove the index is used by displaying and 
-- explaining the actual execution plan)

SELECT ProjName, DevName, dtc.PrcTaskCompl
FROM Project p INNER JOIN Developers_Projects dp ON p.ProjID = dp.ProjID
	INNER JOIN Developer d ON dp.DevID = d.DevID
	INNER JOIN
		(SELECT d.DevID, COUNT(CASE TaskStatus WHEN 'closed' THEN 1 ELSE NULL END) * 100 / COUNT(TaskID) AS PrcTaskCompl
			FROM Developer d INNER JOIN Task t ON d.DevID = t.DevID
			GROUP BY d.DevID
		) dtc
		ON d.devID = dtc.DevID	
WHERE ProjName = 'P2'


SELECT d.DevID, COUNT(CASE TaskStatus WHEN 'closed' THEN 1 ELSE NULL END) * 100 / COUNT(TaskID) AS PrcTaskCompl
			FROM Developer d INNER JOIN Task t ON d.DevID = t.DevID
			GROUP BY d.DevID
			


CREATE NONCLUSTERED INDEX IX_Task_DevID ON Task(DevID) INCLUDE(TaskStatus)
DROP INDEX Task.IX_Task_DevID
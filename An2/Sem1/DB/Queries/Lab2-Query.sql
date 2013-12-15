USE [Sports Manager]

CREATE PROCEDURE AddNewTable
AS
	CREATE TABLE Ball_Types
		(Id int primary key,
		Name nchar(30),
		Diameter int)
GO

EXEC AddNewTable
DROP PROCEDURE AddNewTable


CREATE PROCEDURE DeleteTable
AS
	drop table Ball_Types
GO

EXEC DeleteTable
DROP PROCEDURE DeleteTable

---------------------------------------------------------------------------------------

CREATE PROCEDURE AddNewColumn
AS
	ALTER TABLE Players
	ADD Skin_Colour nchar(30)
GO

EXEC AddNewColumn
DROP PROCEDURE AddNewColumn


CREATE PROCEDURE DeleteColumn
AS
	ALTER TABLE Players
	DROP COLUMN Skin_Colour
GO

EXEC DeleteColumn
DROP PROCEDURE DeleteColumn

---------------------------------------------------------------------------------------

CREATE PROCEDURE ModifyColumnType
AS
	ALTER TABLE Sports
	ALTER COLUMN Name nchar(25)
GO

EXEC ModifyColumnType
DROP PROCEDURE ModifyColumnType


CREATE PROCEDURE UndoModifyColumnType
AS
	ALTER TABLE SPORTS
	ALTER COLUMN Name nchar(30)
GO

EXEC UndoModifyColumnType
DROP PROCEDURE UndoModifyColumnType

---------------------------------------------------------------------------------------

CREATE PROCEDURE AddDefaultConstraint
AS
	ALTER TABLE Sports
	ADD CONSTRAINT default_name DEFAULT 'defaultName' FOR Name
GO

EXEC AddDefaultConstraint
DROP PROCEDURE AddDefaultConstraint


CREATE PROCEDURE RemoveDefaultConstraint
AS
	ALTER TABLE SPORTS
	DROP CONSTRAINT default_name
GO

EXEC RemoveDefaultConstraint
DROP PROCEDURE RemoveDefaultConstraint

---------------------------------------------------------------------------------------

CREATE PROCEDURE CreateForeignKeyConstraint
AS
	ALTER TABLE Players
	ADD Constraint foreignkey
	FOREIGN KEY (ID)references Teams(ID)
GO

EXEC CreateForeignKeyConstraint
DROP PROCEDURE CreateForeignKeyConstraint


CREATE PROCEDURE DropForeignKeyConstraint
AS
	ALTER TABLE Players
	DROP CONSTRAINT foreignkey
GO

EXEC DropForeignKeyConstraint
DROP PROCEDURE DropForeignKeyConstraint

---------------------------------------------------------------------------------

CREATE PROCEDURE CreateTableProcedure
AS
CREATE TABLE dbVersion(versionNo int PRIMARY KEY, upgradeProcedure varchar(50),
		downgradeProcedure varchar(50), currentVersion bit);
GO

EXEC CreateTableProcedure

DROP PROCEDURE CreateTableProcedure
GO

DROP TABLE dbVersion

CREATE PROCEDURE ShowRecords
AS
SELECT * FROM dbVersion
GO

EXEC ShowRecords

DROP PROCEDURE ShowRecords


CREATE PROCEDURE AddRecords
AS
INSERT INTO dbVersion VALUES (1, 'ModifyColumnType', '', 1);
INSERT INTO dbVersion VALUES (2, 'AddDefaultConstraint', 'UndoModifyColumnType', 0);
INSERT INTO dbVersion VALUES (3, 'AddNewTable', 'RemoveDefaultConstraint', 0);
INSERT INTO dbVersion VALUES (4, 'AddNewColumn', 'DeleteTable',0);
INSERT INTO dbVersion VALUES (5, 'CreateForeignKeyConstraint', 'DeleteColumn',0);
INSERT INTO dbVersion VALUES (6, '', 'DropForeignKeyConstraint',0);
GO

EXEC AddRecords

DROP PROCEDURE AddRecords



CREATE PROCEDURE ChangeVersion @version int
AS
BEGIN

IF (@version > 6 OR @version < 1) RAISERROR('Invalid version' ,16,16);
	
DECLARE @currentVersion int
DECLARE @oldVersion int
DECLARE @newVersion int
DECLARE @toExecute varchar(50)

SET @currentVersion = (SELECT versionNo FROM dbVersion WHERE currentVersion = 1)
SET @newVersion = @version
SET @oldVersion = @currentVersion

IF (@currentVersion < @version)
	BEGIN
		WHILE @currentVersion < @version
		BEGIN
			SET @toExecute = (SELECT upgradeProcedure FROM dbVersion WHERE versionNo = @currentVersion)
			EXEC @toExecute
			SET @currentVersion = @currentVersion + 1
		END
	END
ELSE
	BEGIN
		WHILE @currentVersion > @version
		BEGIN
			SET @toExecute = (SELECT downgradeProcedure FROM dbVersion WHERE versionNo = @currentVersion)
			EXEC @toExecute
			SET @currentVersion = @currentVersion - 1
		END
	END

UPDATE dbVersion
	SET currentVersion = 0 WHERE versionNo = @oldVersion
UPDATE dbVersion
	SET currentVersion = 1 WHERE versionNo = @newVersion
END
GO


DROP PROCEDURE ChangeVersion

EXEC ChangeVersion 2
EXEC ShowRecords

DROP TABLE dbVersion
EXEC CreateTableProcedure
EXEC AddRecords
EXEC ShowRecords


EXEC DropForeignKeyConstraint
EXEC DeleteColumn
EXEC DeleteTable
EXEC RemoveDefaultConstraint
EXEC UndoModifyColumnType

SELECT * FROM SYS.default_constraints
SELECT * FROM SYS.objects WHERE type = 'F'
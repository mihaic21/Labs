CREATE VIEW MyView AS SELECT * FROM Sports

SELECT * FROM MyView

INSERT INTO Players_List VALUES ('Mike', 7, 'ger', 213, 14, 8)

DROP VIEW MyView




declare @playerName nchar(30), @homeCountry nchar(30)

declare testCursor cursor
for select Name, Home_Country from Players_List

open testCursor

fetch next from testCursor into @playerName, @homeCountry

while @@FETCH_STATUS = 0
begin
	print @playerName + ' ' + @homeCountry
	fetch next from testCursor into @playerName, @homeCountry
end

close testCursor
deallocate testCursor


select * from sys.tables
USE [Sports Manager]

SELECT * FROM Players_List WHERE Age > 25
SELECT * FROM Players_List WHERE Height BETWEEN 10 AND 200
SELECT * FROM Teams WHERE League_ID = 3
SELECT * FROM Games WHERE Home_Team_ID = 1
SELECT * FROM Sports WHERE First_Game_Year BETWEEN 1850 AND 1900

SELECT Name FROM Teams WHERE League_ID = 1 OR League_ID = 2 GROUP BY Name
SELECT Year_Of_Foundation FROM Teams WHERE League_ID < 3 GROUP BY Year_Of_Foundation
SELECT Age FROM Referees GROUP BY Age HAVING  Age > 30

SELECT DISTINCT Name FROM Sports
SELECT DISTINCT League_ID FROM Teams

SELECT Year_Of_Foundation FROM Teams GROUP BY Year_Of_Foundation HAVING Year_Of_Foundation > 1800
---SELECT Titles, SUM(MVP) FROM Player_Trophies GROUP BY Titles HAVING SUM(MVP) > 5


SELECT DISTINCT Sports.Name, Leagues.Name FROM Sports, Leagues
SELECT DISTINCT Name, Home_Country FROM Players_List UNION SELECT Name, Country FROM Leagues
SELECT Sports.Name, Leagues.Name FROM Sports LEFT JOIN Leagues ON Sports.Name = Leagues.Country


SELECT Sports.Name, Leagues.Name, Locations.Name FROM Sports
	JOIN Leagues ON Sports.ID = Leagues.ID
	JOIN Locations ON Locations.ID = Sports.ID
	
SELECT DISTINCT Teams.Name, Locations.Name, Players_List.Team_ID FROM Teams
	JOIN Locations ON Teams.ID = Locations.Home_Team_ID
	JOIN Players_List ON Teams.ID = Players_List.Team_ID
	
SELECT Referees.Name, Sports.Name, Leagues.Name FROM Referees
	JOIN Sports ON Referees.Sport_ID = Sports.ID
	JOIN Leagues ON Referees.Sport_ID = Leagues.Sport_ID
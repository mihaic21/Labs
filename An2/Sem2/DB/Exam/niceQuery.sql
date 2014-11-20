use [Exam-UserRuns]

select U.userName, R.levelNo, R.overallTime, R.collectedCoins from Runs R
	inner join Games G on R.gameID = G.gameID
	inner join Users U on U.userID = R.userID
	where R.overallTime = (select MIN(overallTime) from Runs where Runs.userID = U.userID) and G.gameOS = 'Android'


create nonclustered index runs_ind3 on Runs(userID) include (gameID, overallTime, collectedCoins, levelNo)
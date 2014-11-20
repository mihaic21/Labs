use [Exam-UserRuns]

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
--SET TRANSACTION ISOLATION LEVEL READ COMMITTED -> for avoiding dirty read

select * from Users where userAlias='alias1'
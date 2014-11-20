use [Exam-UserRuns]

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
begin tran
update Users set userEmail = 'newmail' where userAlias='alias1'
waitfor delay '00:00:10'
rollback tran
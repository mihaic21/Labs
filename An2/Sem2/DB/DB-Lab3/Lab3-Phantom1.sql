use [Sports Manager]

set transaction isolation level repeatable read

begin transaction
select * from Players_List
waitfor delay '00:00:05'
select * from Players_List
rollback transaction
set transaction isolation level read committed

begin tran
select * from Players_List order by Name
waitfor delay '00:00:05.00'
select * from Players_List order by Name
commit tran
begin tran
update Players_List set Home_Country = 'ger' where Home_Country = 'usa'
waitfor delay '00:00:05.00'
rollback transaction
select * from Players_List where Home_Country = 'usa'
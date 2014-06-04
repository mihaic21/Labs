set transaction isolation level read committed

begin tran
update Players_List set Home_Country='nz' where Home_Country='ro'
commit tran
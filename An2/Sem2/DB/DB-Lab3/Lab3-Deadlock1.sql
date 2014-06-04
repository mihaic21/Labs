begin tran

update Players_List set Name='Andrew' where Name='Ben'
waitfor delay '00:00:05.00'
update Player_Trophies set MVP=3 where MVP=2
rollback tran
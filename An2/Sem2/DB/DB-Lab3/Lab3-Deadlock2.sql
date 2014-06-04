begin tran

update Player_Trophies set MVP=3 where MVP=2
waitfor delay '00:00:05.00'
update Players_List set Name='Andrew' where Name='Ben'
rollback tran
create procedure insertPlayerTrophies(@playerName nchar(30), @teamName nchar(30), @homeCountry nchar(30), @height int, @age int, @trophyMVP int, @trophyTitles int)

as
begin

begin tran
set XACT_ABORT ON

declare @teamID int
set @teamID = -1

select @teamID = Teams.ID from Teams where Teams.Name = @teamName

if @teamID = -1
	rollback tran
	else
	begin
		declare @trophyID int
		set @trophyID = -1
		insert into Player_Trophies values (@trophyMVP, @trophyTitles)
		select @trophyID = Player_Trophies.ID from Player_Trophies where Player_Trophies.MVP = @trophyMVP
		
		if @trophyID = -1
			rollback tran
		else
		begin
			insert into Players_List values (@playerName, @teamID, @homeCountry, @height, @age, @trophyID)
			
			declare @playerID int
			set @playerID = -1
			
			select @playerID = Players_List.ID from Players_List where Players_List.Name = @playerName
			
			if @playerID = -1
				rollback tran
			else
			begin
				insert into PTrophies values (@playerID, @trophyID)
			
				commit tran
			end
			
		end
	end

end
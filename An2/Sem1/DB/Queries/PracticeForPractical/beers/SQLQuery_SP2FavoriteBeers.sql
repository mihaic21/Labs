CREATE PROCEDURE CustomersFavoriteBeers
AS
BEGIN
CREATE TABLE #temp1 
(
	customer_id int
	, beer_id int
	, number int
)
	INSERT INTO #temp1
	SELECT customer_id, beer_id, COUNT(*)
	FROM CustomersBeers
	GROUP BY customer_id, beer_id, CAST([time] AS DATE)

	WHILE (SELECT COUNT(*) FROM #temp1) > 0
	BEGIN 
		IF (SELECT TOP 1 number FROM #temp1) >= 5
			INSERT INTO FavoriteBeersList
			VALUES ((SELECT TOP 1 customer_id FROM #temp1), (SELECT TOP 1 beer_id FROM #temp1))		
		DELETE TOP (1) FROM #temp1
	END
END


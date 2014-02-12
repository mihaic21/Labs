SELECT DISTINCT bars.name
FROM BarsBeers bb
JOIN Beers b
ON b.id = bb.beer_id
JOIN Bars
ON bar_id = Bars.id
WHERE bb.price > 2 * b.producer_price
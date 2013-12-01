%=========================== assert2           ============================
/*
   Example for :  ASSERT2,  ASSERTZ2
*/   
domains
   name, address, cityname = string
   age, zipcode = integer
   
database - persons
   person(name, age, address, zipcode)                      /* Name = persons */
database - cities                                           /* Name = cities  */
   city(zipcode, cityname)       
   
goal
   assert(person("Dan", 26, "", 8600), persons),
   assert(person("Kelly", 27, "", 8600), persons),
   assertz(person("Rob", 23, "", 9800), persons),
   assertz(city(8600, "Scotts Valley"), cities),
   assert(city(6800, "Santa Cruz"), cities).

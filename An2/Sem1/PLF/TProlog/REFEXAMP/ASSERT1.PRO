%=========================== assert           ============================
/*
   Example for :  ASSERT1,  ASSERTZ1
*/   
domains
   name, address, cityname = string
   age, zipcode = integer
   
database
   person(name, age, address, zipcode)
   city(zipcode, cityname)
   
goal
   assert(person("Dan", 26, "", 8600)),
   assert(person("Kelly", 27, "", 8600)),
   assertz(person("Rob", 23, "", 9800)),
   assertz(city(8600, "Scotts Valley")),
   assert(city(6800, "Santa Cruz")).

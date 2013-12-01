%=====================      asserta/2             ==========================
DOMAINS
  NAME, ADDRESS, CITYNAME = STRING
  AGE, ZIPCODE = INTEGER

DATABASE - persons
  person(NAME,AGE,ADDRESS,ZIPCODE)

DATABASE - cities
  city(ZIPCODE,CITYNAME)

GOAL	assert(person("Dan",26,"",8600),persons),
	assert(person("Kelly",27,"",8600),persons),
	assert(person("Rob",23,"",9800),persons),
	assert(city(8600,"Scoots Valley"),cities),
	assert(city(6800,"Santa Crutz"),cities).

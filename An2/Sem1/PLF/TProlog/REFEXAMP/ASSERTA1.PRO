%=========================== asserta/1           ============================
DOMAINS
  NAME, ADDRESS, CITYNAME = STRING
  AGE, ZIPCODE = INTEGER

DATABASE
  person(NAME,AGE,ADDRESS,ZIPCODE)
  city(ZIPCODE,CITYNAME)

GOAL	assert(person("Dan",26,"",8600)),
	assert(person("Kelly",27,"",8600)),
	assert(person("Rob",23,"",9800)),
	assert(city(8600,"Scoots Valley")),
	assert(city(6800,"Santa Crutz")).

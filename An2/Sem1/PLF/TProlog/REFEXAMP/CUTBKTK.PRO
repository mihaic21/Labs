%====================== cutbacktrack/1         ===============================
/*
   This program will display the first friend of anyone
   that has at least one friend.
*/   
DATABASE
   person(string)
   friends(string,string)
   
GOAL 
   person(P),
   getbacktrack(Btop),
   friends(P,Friend),
   write(Friend),nl,
   cutbacktrack(Btop).

CLAUSES
   person("Henry Huggins").
   person("Jerome Jones").
   person("Betty Brown").
   friends("Betty Brown","Kathy Williams").
   friends("Jerome Jones","Vibeke Victors").
   friends("Jerome Jones","Donald Smith").

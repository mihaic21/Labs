domains
	elem=integer
	losta=elem*
	
predicates
	elimin_cons(lista,lista)
	elimin_cons(lista,lista,integet)
	
goal
	elimin_cons([1,3,4,2,5,6,8],L),
	write(L).
	
clauses
	elimin_cons(L,X):-
		elimin_cons(L,X,0).
		
	elimin_cons([],[],_).
	
	elimin_cons([A,B|L],X,_):-
		B=A+1,
		!,
		elimin_cons([B|L],X,1).
		
	elimin_cons([_|L],X,SW):-
		SW=1,
		!,
		elimin_cons(L,X,0).
	
	elimin_cons([A|L],[A|X],SW):-
		SW=0,
		!,
		elimin_Cons(L,X,SW).
	
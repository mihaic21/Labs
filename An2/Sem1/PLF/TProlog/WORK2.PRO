%Se da o lista formata din numere intregi si liste de numere intregi.
%Se cere sa se elimine toate secventele (cu cel putin 2 elemente)
% de elemente consecutive care sunt liste.
%EX: [[1,2],3,4,[5],[6,7],8,[9],[10]] => [[1,2],3,4,8]

domains
	elem=integer
	list=elem*
	eleme=i(integer);l(list)
	liste=eleme*
	
predicates
	%check if all the elements from the list are consecutive (1-yes,0-no)
	cons(list,integer)
	
	%remove from the list (lst param.); the result is in the list
	%given as 2nd param; the integet is flag showing if we are in an
	%increasing or decreasing sequence (1-increasing,0-decreasing).
	elim(liste,liste,integer)
	
	%return the last elem from list
	last(list,elem)
	
goal
	L=[l([1,2]),i(3),i(4),l([5]),l([6,7]),i(8),l([9]),l([10])],
	elim(L,R,0),
	write(R).
	
clauses
	cons([_],1),
	cons([A,B|L],C):-
		B=A+1,
		!,
		cons([B|L],C).
	cons(_,0).
	
	last([],-32000).
	last([E],E).
	last([_|T],E):-
		last(T,E).
		
	%if the lists are consecutive
domains
	el = integer
	list = el*
	llist = list*
predicates
	aranj(list,el,list)
	aranjam(list,list,el,list)
	aux(el,list,el,list)
	toate_aranj(list,el,llist)
	sum(list,el)
	evenSum(llist,llist)
clauses
	sum([],0):-!.
	sum([H|T],R):-sum(T,R1),R=H+R1.
	evenSum([],[]):-!.
	evenSum([H|L],[H|R]):-sum(H,R1),R1 mod 2=0, evenSum(L,R).
	evenSum([H|L],R):-sum(H,R1),R1 mod 2=1, evenSum(L,R).
	
	aux(N,_,1,[N]):-!.
	aux(N,[H|T],K,R):-N=H,!,aux(N,T,K,R).
	aux(N,[H|T],K,R):-K2=K-1,aux(N,T,K2,R2),R=[H|R2].
	aux(N,[_|T],K,R):-aux(N,T,K,R).
	aranj([],_,[]):-!.
	aranj(L,K,R):-aranjam(L,L,K,R).
	aranjam(L,[H|_],K,R):-aux(H,L,K,R).
	aranjam(L,[_|T],K,R):-aranjam(L,T,K,R).
	toate_aranj(L,K,R):-findall(X,aranj(L,K,X),P),evenSum(P,R).
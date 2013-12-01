%====================== free/1                ===============================
PREDICATES
  plus(INTEGER,INTEGER,INTEGER)
  numb(INTEGER)

CLAUSES
  /* This predicate implements addition for all flow-patterns */
  plus(X,Y,Z):-bound(X),bound(Y),Z=X+Y.
  plus(X,Y,Z):-free(X),free(Y),bound(Z),numb(X),Y=Z-X.

  numb(0).
  numb(X):-numb(A), X=A+1.

Goal: plus(X,Y,3)
X=0, Y=3
X=1, Y=2
X=2, Y=1
X=3, Y=0
X=4, Y=-1
etc.

  call(ID,TERML):-
	clause(cmp(ID,TERML1),BODY),
	free(ENV),
	unify_terml(TERML,TERML1,ENV),
	unify_body(BODY,ENV).

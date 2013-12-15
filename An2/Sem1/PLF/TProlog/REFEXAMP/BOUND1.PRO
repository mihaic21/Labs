%====================== bound/1               ===============================
DOMAINS
  TERM  = reference var(VID); cmp(FID,TERML); int(INT)
  TERML = reference TERM*
  PREDNAME, FID, VID = SYMBOL
  INT = INTEGER

PREDICATES
  call(PREDNAME,TERML)

CLAUSES
  call("<",[int(X),int(Y)]):-!,
	bound(X),bound(Y),
	X<Y.

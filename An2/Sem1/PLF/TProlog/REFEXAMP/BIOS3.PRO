%======================    bios/3               =============================
PREDICATES
  dosver(REAL)

CLAUSES
  dosver(VERSION):-
	AX=48*256,bios($21,reg(AX,0,0,0,0,0,0,0),reg(VV,_,_,_,_,_,_,_)),
	L=VV div 256, H=VV-256*L, VERSION=H+L/100.0.

GOAL dosver(X), write("\nDos version=",X).

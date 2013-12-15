%======================     bios/4            ===============================
PREDICATES
  rmdir(STRING)

CLAUSES
  rmdir(NAME):-
  		ptr_dword(NAME,DS,DX),
		AX=256*58,
		bios($21,reg(AX,0,0,DX,0,0,DS,0),_,FLAGS),
		% writef("%x",FLAGS),
		bitand(FLAGS,1,CY),
		CY=0.

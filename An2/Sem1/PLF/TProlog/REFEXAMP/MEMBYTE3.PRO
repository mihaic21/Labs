%====================== membyte/3             ===============================
PREDICATES
  test_o 
  test_i

CLAUSES
  test_o:-
	A="Hello World",
	ptr_dword(A,Seg,Off), Off1=Off+6,
	membyte(Seg,Off1,CH),
	writef("\nChar1=%c",CH).
	
  test_i:-
	str_len(S,13),
	ptr_dword(S,Seg,Off), Off1=Off+6,
	membyte(Seg,Off1,'W'),
	writef("\nString=|%|",S).

GOAL 
   test_o, test_i.

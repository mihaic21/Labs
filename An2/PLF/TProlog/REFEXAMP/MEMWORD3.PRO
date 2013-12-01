%====================== memword/3             ===============================
PREDICATES
  test_o test_i

CLAUSES
  test_o:-
	A="Hello World",
	ptr_dword(A,Seg,Off), Off1=Off+6,
	memword(Seg,Off1,Word),
	bitand(Word,$ff,CH1), bitright(Word,8,CH2),
	writef("\nChar1=%c, Char2=%c",CH1,CH2).
	
  test_i:-
	str_len(S,13),
	ptr_dword(S,Seg,Off), Off1=Off+6,
	WORD='W'+'o'*$100,
	memword(Seg,Off1,Word),
	writef("\nString=|%|",S).

GOAL test_o, test_i.

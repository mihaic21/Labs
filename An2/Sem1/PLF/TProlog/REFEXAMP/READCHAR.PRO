%====================== readchar/1            ===============================
GOAL	write("\nEnter a character: "),
	readchar(CH1),
	write("\nThe character was: ",CH1),
	CH1=0,
	write("\nIt was a function key"),
	readchar(CH2),
	writef("\nKey code1=%d:'%c', Key code2= %d:'%c'",CH1,CH1,CH2,CH2).

%====================== readdevice/1          ===============================
DOMAINS
  FILE = input

GOAL	openread(input,"dd.txt"),
	readdevice(input),
	readln(L1),write(L1),nl,
	readln(L2),write(L2),nl,
	readdevice(keyboard),
	write("Enter text: "),
	readln(L3),write(L3),nl.

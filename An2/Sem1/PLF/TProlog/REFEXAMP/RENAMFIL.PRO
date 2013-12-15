%====================== renamefile/2          ===============================
GOAL	file_str("dd.txt", "Punks walking in LA?"),
	renamefile("dd.txt", "dd1.txt"),
	file_str("dd1.txt", X),
	deletefile("dd1.txt"),
	write(X),nl.

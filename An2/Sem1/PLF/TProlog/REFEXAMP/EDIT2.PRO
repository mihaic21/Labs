%====================== edit/2                ===============================
GOAL	file_str("dd.txt",X),
	edit(X,X1),
	X1><X,
	file_str("dd.txt",X1).

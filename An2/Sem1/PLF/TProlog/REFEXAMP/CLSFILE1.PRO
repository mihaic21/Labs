%====================== closefile/1           ===============================
DOMAINS
  FILE = myfile

GOAL	openwrite(myfile,"dd.txt"),
	writedevice(myfile),
	write("File with one line\n"),
	closefile(myfile).


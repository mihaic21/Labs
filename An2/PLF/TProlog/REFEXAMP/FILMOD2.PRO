%====================== filemode/2            ===============================
DOMAINS
  FILE = reflexfile

GOAL	openread(reflexfile,"xreflex.rxd"),
	readdevice(reflexfile),
	filemode(reflexfile,0),
	% Use only readchar from here
	closefile(reflexfile).

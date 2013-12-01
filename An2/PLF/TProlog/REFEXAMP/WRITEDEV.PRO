%====================== writedevice/1         ===============================
DOMAINS
  FILE = myfile

GOAL	openwrite(myfile,"dd.txt"),
	writedevice(myfile),
	write("File with one line\n"),
	writedevice(screen),
	write("\nNow writing to the screen"),
	writedevice(myfile),
	closefile(myfile),
	writedevice(DEV),
	write("\nNote write device is automatically set to: ",DEV),
	write("\nwhen the file is closed").

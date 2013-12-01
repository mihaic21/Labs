%====================== flush/1               ===============================
GOAL	writedewice(printer),
	write("Hello"),
	flush(printer),
	writedevice(screen),
	write("\nPress return),
	readln(_).

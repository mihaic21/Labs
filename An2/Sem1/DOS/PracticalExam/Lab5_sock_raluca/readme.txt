Implement two programs, client and server, that communicate through
TCP sockets. The server should handle clients concurrently using a
limited number of threads (there can be at most N active threads at
any moment). The clients will send strings of text of variable lengths
to the server. Server will store all these strings in a file
named log.txt, along with the clients' IP and port. Sends back to
the client the word "DONE" and the total number of entries logged in
the file. Use the necesary synchronization mechanisms.


For the server:
gcc -o server server.c -lpthread -lm --> to compile
./server --> to run

The server will ask for a port number and the maximum number of threads

For the clinet:
gcc -o client client.c --> to compile
./client

The client will ask for the port number and the message

Futher explanations can be found in the comments from the source code.

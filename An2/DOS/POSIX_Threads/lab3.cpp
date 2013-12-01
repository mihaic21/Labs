#include <pthread.h>
#include <cstdlib>
#include <iostream>
#include <fstream>


using namespace std;

pthread_t threads[10];
pthread_mutex_t mut;


string reverse(string line1, string line2){
	string s = line2;
	s.append("\n");
	s.append(line1 + "\n");
	return s;
}


void *threadFunction(void *var){
	char *aux = (char *) var;
	//string s = string(aux);
	ifstream file;
	string line1;
	string line2;
	string result;
	file.open(aux, ifstream::in);
	while (file.good()){
		getline(file, line1);
		if (getline(file,line2)){
			//getline(file,line2);
			result.append(reverse(line1,line2));
		}
		else result.append(line1 + "\n");
		
	}
	
	pthread_mutex_lock(&mut);
	cout << result << endl << endl;
	pthread_mutex_unlock(&mut);
	return NULL;
}


int main(int argc, char* args[]){
	pthread_mutex_init(&mut,NULL);
	int i;
	string s;
	for (i=1; i<argc; i++)
		pthread_create(&threads[i-1], NULL, threadFunction, args[i]);
	
	for (i=1; i<argc; i++)
		pthread_join(threads[i-1], NULL);
	
	pthread_mutex_destroy(&mut);
	return 0;
}


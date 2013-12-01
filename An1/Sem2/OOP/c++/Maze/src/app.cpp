//============================================================================
// Name        : Maze.cpp
// Author      : Mihai Costiug
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include "Maze.h"
#include <stdlib.h>
#include <string.h>
#include "tests.h"
using namespace std;

int main(){
	setbuf(stdout, NULL);
	testAll();
	Maze* mymaze = new Maze();
	mymaze->createMaze();

	int op;

	while(1){
		cout <<"   [1] Display the maze"<<endl;
		cout <<"   [2] Show the shortest path"<<endl;
		cout <<"   [0] Exit"<<endl;
		cout <<"   Please insert command: "<<endl;
		cin >> op;
		switch(op){
			case 1:{
				mymaze->displayMaze();
				break;
			}
			case 2:{
				matrix[starti][startj]=1;
				coord[1][1]=starti;
				coord[1][2]=startj;
				mymaze->back(starti,startj,2);
				cout<<"The shortest path is: "<<endl;
				mymaze->showSol();
			    break;
			}
			case 0:{
				exit(0);
				break;
			}
			default:{
				cout<<"Please enter a correct command: (0-2)";
				break;
			}
		}
	}
	return 0;
}

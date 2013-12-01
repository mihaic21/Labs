/*
 * Maze.h
 *
 *  Created on: Jun 20, 2013
 *      Author: mihai
 */

#ifndef MAZE_H_
#define MAZE_H_
#include <iostream>
#include <fstream>
#include "PQOverDynamicList/DynamicList.h"
#include "PQOverDynamicList/PQOverLIST.h"
#include "PQOverHEAP/PQOverHeap.h"
#include <time.h>
using namespace std;


// Used for moving within the maze.
const int movei[4]={-1,0,1,0};
const int movej[4]={0,1,0,-1};
int coord[100][3];
char matrix[30][30],auxmat[30][30];

// Queue to store path. Will store in the opposite order.
PQueue<int> q;
// Queue to store path in the desired order.
PQueue<int> aux;

//To test the heap implementation
//PriorityQueue q;

// integers. Coordinates of start and end points.
int starti,endi,startj,endj;

/*
File containing the input data.
MUST contain 2 integers at the beginning, representing the number of rows and columns.
MUST contain a "maze" with characters, normally * representing a free position and X will show a wall.
 */
ifstream f("maze.in");


class Maze
{
private:
	// r,c - integers, representing the number of rows and columns, respectively.
	int r, c;
	int i,j;
	int min;

public:

	/*
	  Constructor.
	*/
	Maze();

	/*
	  Destructor.
	*/
	~Maze();

	/*
	  Will generate a matrix with r rows and c columns.
	  r and c will be read from the file "maze.in", where they will be the first 2 numbers.
      The elements will be characters and will be read from the existing file "maze.in".
      The last 4 elements in the file will be integers and will represent:
					- the starting point's coordinates (i,j)
	                - the ending point's coordinates (i,j)
	*/
	void createMaze();



	/*
	  Will display the matrix after it has been created.
	*/
	void displayMaze();


	/*
	  Boolean function checking whether a position is inside the maze.
	  Returns true if it is, false otherwise.
	*/
	bool isInside(int r,int c);


	/*
	  Displays the shortest path (enumeration of pairs of coordinates).
	*/
	void showSol();


	/*
	  Stores the coordinates.
	*/
	void keep(int steps);

	/*
	  Finds the solution.
	*/
	void back(int i,int j, int steps);

};


/*
  Constructor.
  Initializes the constant min with a big number, 1000, and the queues q and aux with empty queues.
  min - integer;
  q - queue;
*/
Maze::Maze()
{
	min = 1000;
	q.initEmpty();
	aux.initEmpty();
}


/*
  Destructor.
*/
Maze::~Maze()
{

}


/*
  Creating the matrix with characters, representing the maze.
  All input data will be read from the file "maze.in".
  NOTE: The first 2 elements in the file MUST be integers, representing the number of rows and columns, respectively.
        The last four MUST be integers, representing the starting coordinates and ending coordinates.
  The start/end points may be any character other than X.
  X will represent a wall/obstacle.
  * will represent valid positions through which the robot can pass.
  The start/end positions can be * as well, unless desired to be S/G (their coordinates at the end of the file is what matters).

*/
void Maze::createMaze()
{
	f >> r; // Reading the number of rows. The first number in the input file.
	f >> c; // Reading the number of columns. The second number in the input file.

	for(i = 1; i <= r; i++)
		for(j = 1; j <= c; j++)
			{
				f >> matrix[i][j];
				auxmat[i][j] = matrix[i][j];
		    }

    // Reading the starting and ending coordinates from the file. Last four in the file.
	f >> starti;
	f >> startj;
	f >> endi;
	f >> endj;
	f.close();
}



/*
  Displaying the matrix representing the maze.
*/
void Maze::displayMaze()
{
	for(i = 1; i <= r; i++)
	{
		for(j = 1; j <= c; j++)
			cout << auxmat[i][j]<<" ";
		cout<< endl;
	}
}



/*
  Checking whether the robot is still in the maze.
  Returns true if it is, false otherwise.
*/
bool Maze::isInside(int i,int j)
{
	if(i >= 1 && i <= r && j >= 1 && j <= c)
		return true;
	return false;
}




/*
  Stores solutions into the queues q
  The solutions will be considered as pairs of 2 integers, the 2 coordinates.
*/
void Maze::keep(int steps)
{
	if(steps < min)
	{
		min = steps;
		for(i = 1; i <= steps; i++)
		{
			// Keeping the coordinates in the queue
			q.enqueue(coord[i][1]);
			q.enqueue(coord[i][2]);
		}
	}
}



void Maze::back(int currentx,int currenty, int steps)
{
	int i, j,k;

	if(currentx == endi && currenty == endj)
		keep(steps-1);
	else
		for(k = 0; k <= 3; k++)
		{
			i = currentx + movei[k];
			j = currenty + movej[k];
			if((isInside(i,j)==true) && ((matrix[i][j] == '*') || (matrix[i][j] == 'S') || (matrix[i][j] == 's') || (matrix[i][j] == 'G') || (matrix[i][j] == 'g')))
			{
				matrix[i][j] = steps;
				coord[steps][1] = i;
				coord[steps][2] = j;
				back(i,j,steps+1);
				matrix[i][j] = 0;
			}
		}
}

/*
  Will display the path by showing the coordinates that were stored in the queue, pairs of 2 integers,
  first one representing the row, second one the column.

 q - queue containing integers, previously added, representing coordinates.
 We need an auxiliary queue aux because the elements are stored in the opposite order in q (for avoiding
 traversing the entire queue when adding an element or removing one)
 aux - auxiliary queue
 x,y -  integers. Coordinates.
*/
void Maze::showSol()
{
	int x, y;

	while(!q.isEmpty())
	{
		y = q.dequeue();
		x = q.dequeue();
		aux.enqueue(x);
		aux.enqueue(y);
	}
	while(!aux.isEmpty())
	{
		y = aux.dequeue();
		x = aux.dequeue();
		cout <<"("<<x << ","<<y<<")"<<" ";
	}

/*
	while(q.getSize() != 0)
	{
		x = q.dequeue();
		y = q.dequeue();
		aux.enqueue(x);
		aux.enqueue(y);
	}

 int v[30];
 int i=0;
	while(!aux.isEmpty())
	{
		y = aux.dequeue();
		v[i++]=y;
		x = aux.dequeue();
		v[i++]=x;
	}
	for(j=1;j<i-2;j=j+2)
		cout <<"("<<v[j] << ","<<v[j+1]<<")"<<" ";
*/

	cout<<endl<<endl;

}


#endif /* MAZE_H_ */

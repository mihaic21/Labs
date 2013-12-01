/*
 * tests.h
 *
 *  Created on: Jun 20, 2013
 *      Author: mihai
 */

#ifndef TESTS_H_
#define TESTS_H_
#include "PQOverHEAP/PQOverHeap.h"
#include "PQOverDynamicList/DynamicList.h"
#include "PQOverDynamicList/PQOverLIST.h"

void testAll()
{
	cout<<"                  Testing the list                 "<<endl;
linkedList<int> l;
l.enqueue(4);
l.enqueue(5);
assert(l.isEmpty() == false);
assert(l.remove() == 5);
	cout<<"---------------------Done--------------------------"<<endl<<endl;

	cout<<"                  Testing the heap                 "<<endl;
PriorityQueue q;
q.enqueue(5);
q.enqueue(4);
q.enqueue(1);
q.enqueue(3);
assert(q.getSize() == 4);
assert(q.dequeue() == 5);
	cout<<"---------------------Done--------------------------"<<endl<<endl;
}



#endif /* TESTS_H_ */

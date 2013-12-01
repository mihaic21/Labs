/*
 * PQOverLIST.h
 *
 *  Created on: Jun 20, 2013
 *      Author: mihai
 */

#ifndef PQOVERLIST_H_
#define PQOVERLIST_H_
#include "DynamicList.h"

/*
    Creating the queue structure implemented over a dynamic linked list
    Operations: initEmpty, isEmpty, isFull, dequeue, enqueue, show
*/
template <typename TE>
class PQueue
{
	int nr; // will keep track of the number of elements in the priority queue
	linkedList<TE> l;

	public:

	   /*
		Creates the empty queue
	   */
      void initEmpty();


	  /*
	    Inserting element el into the queue (acts like insertFirst)
	  */
      void enqueue(TE el);


	  /*
	    Removing an element from the queue and returning it.
	  */
      TE dequeue();


	  /*
	    Boolean function checking whether the queue is empty or not
	    Returns true if it's empty. False otherwise.
	  */
	  bool isEmpty();


	  /*
	    Boolean function checking whether the queue is full or not.
	    Returns true if it's full. False otherwise.
	  */
	  bool isFull();


	  /*
	    Returns the element with the biggest priority from the queue
	  */
	  TE peek();


	  /*
	    Showing the elements from queue
	  */
      void show();


	  /*
	    Destroying queue. All memory is released.
	    No operations will be allowed.
	  */
	  void destroy();

	  /*
	   * returns the no. of elemens from the queue
	   */
	  int getSize(){return nr;}
};


/*
  Initializing the empty queue
*/
template <typename TE> void PQueue<TE>::initEmpty()
{
	l.initEmpty();
}


/*
  Checking whether the queue is empty or not.
  Returns true if it is; false otherwise.
*/
template <typename TE> bool PQueue<TE>::isEmpty()
{
	return l.isEmpty();
}


/*
  Checking whether the queue is full or not.
  Returns true if it is; false otherwise.
*/
template <typename TE> bool PQueue<TE>::isFull()
{
	return l.isFull();
}


/*
  Adding an element of type TE into the queue
  The element will be added as the first element in the queue
  Queue cannot be full.
*/
template <typename TE> void PQueue<TE>::enqueue(TE el)
{
	if(l.isFull() == true)
		cout << "Cannot add into queue. The queue is full. "<< endl;
	else
	{
		l.enqueue(el);
	}
}


/*
  Removing an element of type TE from the queue, and returning its value.
  The queue cannot be empty.
  The removed element will always be the one with the biggest priority from the queue, thus, the first
*/
template <typename TE> TE PQueue<TE>::dequeue()
{
	return l.remove();
}


/*
  Returns the element with the biggest priority
*/
template <typename TE> TE PQueue<TE>::peek()
{
		return l.top();
}


/*
  Displays the queue's elements.
*/
template <typename TE> void PQueue<TE>::show()
{
	l.printEls();
}


/*
  Removing everything from the queue
  No operations can be performed after this is performed.
*/
template <typename TE> void PQueue<TE>::destroy()
{
	l.destroyList();
}


#endif /* PQOVERLIST_H_ */

/*
 * DynamicList.h
 *
 *  Created on: Jun 20, 2013
 *      Author: mihai
 */

#ifndef DYNAMICLIST_H_
#define DYNAMICLIST_H_
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

/*
    Creating the node structure.
    The node will contain the data (value), and a pointer to the next node.
    data: of type TE.
*/
template <typename TE>
struct node
{
   TE data;
   struct node *next;
};

int cap = 100;


/*
    Creating the linked list.
    Operations: initEmpty, isEmpty, isFull, insert, remove, top, printEls, destroy.
*/
template <typename TE>
class linkedList
{
	int nr; 			// will keep track of the number of elements in the list.
	struct node<TE> *head;
	public:

	   /*
		Creates empty list.
	   */
      void initEmpty();


	  /*
	    Inserting element el into the list.
	  */
      void enqueue(TE el);


	  /*
	    Removing an element from the list and returning it.
	  */
      TE remove();


	  /*
	    Boolean function checking whether the list is empty or not.
	    Returns true if it's empty. False otherwise.
	  */
	  bool isEmpty();


	  /*
	    Boolean function checking whether the list is full or not.
	    Returns true if it's full. False otherwise.
	  */
	  bool isFull();


	  /*
	    Returns the element at the top (the head) of the list.
	  */
	  TE top();


	  /*
	    Showing the elements in the list.
	  */
      void printEls();


	  /*
	    Destroying list. All memory is released.
	    No operations will be allowed.
	  */
	  void destroyList();
};


/*
  Initializing the empty list.
*/
template <typename TE> void linkedList<TE>::initEmpty()
{
	head = NULL;
	nr = 0;
}


/*
  Checking whether the list is empty or not.
  Returns true if it is. False otherwise.
*/
template <typename TE> bool linkedList<TE>::isEmpty()
{
	if(head == NULL)
		return true;
	return false;
}


/*
  Checking whether the list is full or not.
  Returns true if it is. False otherwise.
*/
template <typename TE> bool linkedList<TE>::isFull()
{
	if(nr > cap)
		return true;
	return false;
}


/*
  Adding an element of type TE into the list.
  The element will be added at the top of the list.
  The list cannot be full.
*/
template <typename TE> void linkedList<TE>::enqueue(TE el)
{
		struct node<TE> *n;
		n = new node<TE>;
		n->data = el;
		n->next = NULL;
		if(head != NULL)
			n->next = head;
		head = n;
		nr++;
}


/*
  Removing an element of type TE from the list, and returning its value.
  The list cannot be empty.
  The removed element will always be the one at the top.
*/
template <typename TE> TE linkedList<TE>::remove()
{
	TE a;
	a = 0;
	if(isEmpty() == true)
	{
		cout << "Empty. Nothing to remove! " << endl;
		return a;
	}
	struct node<TE> *temp;
	temp = head;
	a = top();
	head = temp->next;
	delete temp;
	nr--;
	return a;
}


/*
  Returns the element at the top of the list.
*/
template <typename TE> TE linkedList<TE>::top()
{
	TE a;
	a = head->data;
	return a;
}


/*
  Displays the list elements.
*/
template <typename TE> void linkedList<TE>::printEls()
{
   struct node<TE> *ptr = head;
   while(ptr != NULL)
   {
      cout << ptr->data<<" -> ";
      ptr = ptr->next;
   }
   cout << " NULL " << endl;
}


/*
  Removing everything from the list.
  No operations can be performed after this is performed.
*/
template <typename TE> void linkedList<TE>::destroyList()
{
	while(head != NULL)
    {
        struct node<TE>* temp;
        temp = head;
		head = temp->next;
        delete temp;
    }
	delete head;
}


#endif /* DYNAMICLIST_H_ */

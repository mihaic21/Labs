/*
 * list.h
 *
 *  Created on: Apr 19, 2013
 *      Author: Amalia
 */

#ifndef LIST_H_
#define LIST_H_

#include <iostream>
#include <assert.h>

using namespace std;

//------------------------CLASS NODE-----------------------------------

template<typename TElem> class LinkedList;
template<typename TElem> class Iterator;
//I have written the prototypes here so that I can use them
//in things declared before them

template<typename TElem>
class Node {
private:
	Node* next;
	TElem value;

public:
	friend class LinkedList<TElem> ;
	friend class Iterator<TElem> ;
	Node(Node* next, TElem e);

	void swapContent( Node* j){
			Node* aux = new Node(this->getNext(), this->getValue());
			this->setValue(j->getValue());
			j->setValue(aux->getValue());
	}


	void setNext(Node* newN) {
		this->next = newN;
	}
	Node* getNext() {
		return this->next;
	}
	TElem getValue(){
		return this->value;
	}
	void setValue(TElem v){
		this->value = v;
	}
};

//------------------------CLASS ITERATOR-----------------------------------

template<typename TElem>
class Iterator {
private:
	Node<TElem>* cursor;
public:
	Iterator(Node<TElem>* n) {
		cursor = n;

	}
	TElem& operator*() {		 //dereferencing operator
		//I get what cursor is at
		return cursor->value;
	}

	Node<TElem>* getNode() {
		return cursor;
	}

	bool operator !=(Iterator<TElem>& other) {
		return this->cursor != other.cursor;
	}
	Iterator& operator ++() {
		this->cursor = this->cursor->next;
		return *this;			 //I return a pointer to this, because if I returned this,
		//I'd actually create a new object and I don't want that
	}
};

//-------------------------CLASS LINKED LIST----------------------------

template<typename TElem>
class LinkedList {
private:
	int len;
	Node<TElem>* head;
	Node<TElem>* tail;

public:
	LinkedList();
	LinkedList(const LinkedList& l2);
	void add(const TElem& e);
	void del(const int pos);



	//	LinkedList& operator = (LinkedList anotherList){
	//
	//
	//	}
	TElem getElementByPosition(const int pos);

	//I define size here - since it's quite short
	int getSize() const { 		// ^^ The const in the end of the class
		//says that this function will not modify any attributes,
		// - it will just return a value
		return this->len;
	}
	Node<TElem>* copy() const;


	//..................................................
	typedef Iterator<TElem> It; //Actually I just rename
	//Iterator<TElem> to It
	It getIterator();
	It begin();
	It end();
};

//-----------------------------------------------------------------------

template <typename TElem>
LinkedList<TElem>::LinkedList(const LinkedList<TElem>& l2){
	head = l2.copy();
}

template<typename TElem>
void LinkedList<TElem>::add(const TElem& e) {
	if (head == 0) { //If I have nothing in the list
		head = tail = new Node<TElem>(0, e); //Make the node point to nothing and hold e
	} else { //If I do have something
		Node<TElem>* n = new Node<TElem>(0, e); //the node that I'm adding will have as prev elem the tail
		tail->next = n; //I rebuild the connections, i.e. I make the current tail's next elem
		//will be the newly added node
		tail = n; //and the tail becomes the new added node
	}
	len++;
}

//-----------------------------------------------------------------------

template<typename TElem>
void LinkedList<TElem>::del(const int pos) {
	LinkedList<TElem>::It current = this->begin();
	LinkedList<TElem>::It trail = 0; //always the previous of current
	LinkedList<TElem>::It end = 0;
	int i = 0;
	if (this->head == 0) {
		cout << "The list is empty. Nothing to be deleted." << endl;
	} else
		while (current != end) {
			if (i == pos) {
				break;
				//I will exit the loop and do the actual deleting
			}
			i++;										//if the element wasn't found, I go to the next position
			trail = current;							//trail becomes current
			++current;									//current advances to the next element
		}
	if ((*current) == 0) {
		cout << "No element in the list with the required specifications was found." << endl;
	} else {
		//i have 2 cases:
		//case1 - i have to delete from the first position
		if (pos == 0) {
			this->head = this->head->next;
			delete current.getNode();
		} else
			//case2 - i have to delete from beyond the first pos
		{
			Node<TElem>* del = current.getNode();		//the element to be deleted
			Node<TElem>* t = trail.getNode();			//the element for which the connections are rebuilt
			t->setNext(del->getNext());					//the next of trail will be the next of current
			delete del;									//and I delete the one I don't need anymore
		}
		(this->len)--;									//i decrement the number of nodes
	}
}

//-----------------------------------------------------------------------

template <typename TElem>
TElem LinkedList<TElem>::getElementByPosition(const int pos){
	LinkedList<TElem>::It current = this->begin();
	if (pos>len){
		return 0;
	}
	int i=0;
	while (i!=pos){
		i++;
		++current;
	}
	return *current;
}

//-----------------------------------------------------------------------

template <typename TElem>
Node<TElem>* LinkedList<TElem>::copy() const{
	if(head == NULL)
		return NULL;
	else
	{
		Node<TElem>* newHead = new Node<TElem>(0, head->getValue());
		Node<TElem>* crt = head->getNext();
		Node<TElem>* newCrt = newHead;
		Node<TElem>* temp;
		while(crt != NULL)
		{
			temp = new Node<TElem>(0, crt->getValue());
			newCrt->setNext(temp);
			newCrt = temp;
			crt = crt->getNext();
		}
		return newHead;
	}
}

//-----------------------------------------------------------------------
template<typename TElem>
Iterator<TElem> LinkedList<TElem>::getIterator(){
	return LinkedList<TElem>::It(this);
}

//-----------------------------------------------------------------------

template<typename TElem>
LinkedList<TElem>::LinkedList() { //generic constructor
	this->len = 0;
	this->head = 0; //here, 0 = NULL
	this->tail = 0;
}

template<typename TElem>
Node<TElem>::Node(Node* next, TElem e) {
	this->next = next;
	this->value = e;
}

template<typename TElem>
Iterator<TElem> LinkedList<TElem>::begin() {
	//returned type	//class-name	 ::function-name
	return Iterator<TElem>(head);
}

template<typename TElem>
Iterator<TElem> LinkedList<TElem>::end() {
	return Iterator<TElem>(0); //I return an iterator initialized
	//with the end of the tail
	//The end of the tail is 0 for simply-doubly-linked lists, and the head for circular ones.
}

void testList();

#endif /* LIST_H_ */

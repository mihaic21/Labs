#ifndef VECTORDINAMIC_H_
#define VECTORDINAMIC_H_

#include <iostream>
#include <assert.h>
#include "../Domain/question.h"

using namespace std;

template <typename TElem> class Vector{

//i.e. i can use for eg. <int> Vector - for vector of ints
//or 				<expense*> Expense - for vector of expenses

private: 					//here go the attributes that cannot be seen
							//but by frieeeend classes
	TElem* elems;
	int size;
	int capacity;


public:
	/* Constructor
	 * Creates a dynamic vector, containing generic elements
	 * Input: 	-
	 * Output: new Vector; v.size = 0; v.capacity = 10;
	 */
	Vector(){				//a generic constructor
		this->size = 0;		//the current object's size is set to 0
		this->capacity = 30;
		this->elems = new TElem[this->capacity];
							//i.e. allocate space
							//for 10 elements of type TN
	}

	//-----------------------------------------------------------------------
	/* Destructor
	 * Deallocates the memory occupied by the elements of the dynamic vector
	 * Input: -
	 * Output: memory is deallocated
	 */
	~Vector(){				//destructor
							//called with "delete v"
		if (this->elems != NULL){
			delete [] this->elems;
			this->elems = NULL;
		}
	}
	//-----------------------------------------------------------------------
	/*
	 * Adds an element to a dynamic vector
	 * Input: e - generic element
	 * Output: v' = v U {e}
	 */
	 void add(TElem e){		//called with v->add(e)
		 if (this->size==this->capacity-2){
			 this->resize();
		 }
		this->elems[this->size] = e;
		this->size++;
	 }

	//-----------------------------------------------------------------------
	/*
	 * Deletes an element from a dynamic vector
	 * Input: poz - position of element to be deleted
	 * Output: v' = v \ el from position poz
	 */
	 void del(int poz){		//called with v->del(poz)
		for (int i = poz; i < this->size-1; i++){
			this->elems[i] = this->elems[i+1];
		}
		this->size--;
	}

	 /*
	  * Copy constructor
	  */
	 Vector(const Vector &dv); //functie de copiere intre doua elem de tip vector. Ex: vector1=vector2;

	 /*
	  * Assignment operator
	  */
	 Vector& operator=(const Vector& dv);

	 TElem* getElems(){
		 return this->elems;
	 }

	int getSize() const{
		return this->size;
	}

	int getCapacity() const{
		return this->capacity;
	}

	TElem getElementByPosition(int pos) const;

private:
	void resize(){
		this->capacity = 2*this->capacity;
		TElem* newelems = new TElem[this->capacity];
		for (int i = 0; i<this->size; i++)
			newelems[i] = this->elems[i];
		delete [] this->elems;
		this->elems = newelems;
	}
};


//-----------------------------------------------------------------------
/*
 * Now I create a template for returning an element from a vector
 *  having given the position
 * Returns the element on a given position in the dynamic vector
 * Input: 	pos - integer number
 * Output: 	NULL, if pos < 0 or pos > v.len
 * 			v[pos], otherwise
 */
template <typename TElem>
TElem Vector<TElem>::getElementByPosition(int pos) const{
//i.e. type-of-returned-elems Vector<type-of-elements-in-vect>::name-of-fct(params)
	if (pos<0 || pos>this->getSize())
		return NULL;
		else
		{
			return this->elems[pos];
		}
};

template <typename TElem>
Vector<TElem>::Vector(const Vector<TElem> &dv) { //functie de copiere intre doua elem de tip vector. Ex: vector1=vector2;
	this->size = dv.size;
	this->capacity = dv.capacity;

	if (this->elems != NULL){
		delete [] this->elems;
	}
	this->elems = dv.elems;

//	for (int i = 0; i < this->size; i++) {
//		this->elems[i] = dv.elems[i];
//	}
}

//-----------------------------------------------------------------------
// Test function for class vector

void testVector();

#endif

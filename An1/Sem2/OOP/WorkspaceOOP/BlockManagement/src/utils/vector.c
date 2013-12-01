/*
 * vector.c
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */
#include <stdlib.h>
#include "vector.h"
#include "elem.h"

void create(vector* v, Delete del, Copy cpy){
	/*
	 * Creates the vector
	 */
	if (v != NULL){
		v->del = del;
		v->cpy = cpy;
		v->len = 0;
		v->size = 15;
		v->elems = (Elem)malloc(v->size * sizeof(Elem));
	}
}

void doRealloc(vector* v){
	/*
	 * Reallocates memory for vector
	 */
	v->size += 10;
	Elem* tempElems = v->elems;
	v->elems = malloc(v->size*sizeof(Elem));
	int i;
	for(i=0; i < v->len; i++)
		v->elems[i] = tempElems[i];
	free(tempElems);
}

void add(vector* v, Elem e){
	/*
	 * Adds an element to the vector
	 */
	if(v->len >= v->size)
		doRealloc(v);
	Elem temp = v->cpy(e);
	v->elems[v->len] = temp;
	v->len++;
}

Elem get(vector* v, int pos){
	/*
	 * Retrieves an element from the vector, given the position
	 */
	Elem temp = v->cpy(v->elems[pos]);
	return temp;
}

void destroy(vector* v){
	/*
	 * Destroys vector
	 */
	int i;
	for(i=0; i < v->len; i++)
		v->del(v->elems[i]);
	free(v);
	v=0;
}

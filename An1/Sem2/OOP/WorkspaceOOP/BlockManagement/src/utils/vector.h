/*
 * vector.h
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */

#ifndef VECTOR_H_
#define VECTOR_H_
#include "elem.h"

typedef struct {
	Elem* elems;
	int len;
	int size;
	Delete del;
	Copy cpy;
} vector;

void create(vector* v,  Delete del, Copy cpy);
void add(vector* v, Elem e);
Elem get(vector* v, int pos);
void destroy(vector* v);

#endif /* VECTOR_H_ */

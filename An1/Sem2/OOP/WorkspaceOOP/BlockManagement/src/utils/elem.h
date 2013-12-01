/*
 * elem.h
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */

#ifndef ELEM_H_
#define ELEM_H_

typedef void* Elem;
typedef void (*Delete)(Elem);
typedef Elem (*Copy)(Elem);

#endif /* ELEM_H_ */

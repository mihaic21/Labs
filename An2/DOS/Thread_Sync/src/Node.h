/*
 * Node.h
 *
 *  Created on: Dec 2, 2013
 *      Author: mihai
 */

#ifndef NODE_H_
#define NODE_H_

typedef struct elem{
    int value;
    int worker;
    struct elem *next;
} Node;


#endif /* NODE_H_ */

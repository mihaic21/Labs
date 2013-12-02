/*
 * LinkedList.h
 *
 *  Created on: Dec 2, 2013
 *      Author: mihai
 */

#ifndef LINKEDLIST_H_
#define LINKEDLIST_H_

#include <iostream>
#include "Node.h"

class LinkedList {
public:
    Node *firstNode;
    LinkedList();
    ~LinkedList();
    int numberOfElements();
    void removeNode(Node *node);
    void printList();
};

#endif /* LINKEDLIST_H_ */

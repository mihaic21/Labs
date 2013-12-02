/*
 * LinkedList.cpp
 *
 *  Created on: Dec 2, 2013
 *      Author: mihai
 */


#include "LinkedList.h"

LinkedList::LinkedList(){
	Node *firstNode = NULL;
}

LinkedList::~LinkedList() {
    delete firstNode;
}

int LinkedList::numberOfElements() {
    Node *currNode = this->firstNode;
    int numberOfElements = 0;

    while (currNode != nullptr) {
        numberOfElements++;
        currNode = currNode->next;
    }

    return numberOfElements;
}

void LinkedList::removeNode(Node *node) {
    Node *currNode = this->firstNode;
    Node *nextNode = currNode->next;

    if (this->firstNode == node && this->firstNode != nullptr) {
        this->firstNode = this->firstNode->next;
        return;
    }

    while (nextNode != nullptr) {
        if (nextNode == node) {
            currNode->next = nextNode->next;
            nextNode = currNode->next;
        } else {
            currNode = nextNode;
            nextNode = nextNode->next;
        }
    }
}

void LinkedList::printList() {
    Node *currNode = this->firstNode;
    std::cout << "[" << " ";
    while (currNode != nullptr) {
        std::cout << currNode->value << " ";
        currNode = currNode->next;
    }
    std::cout << "]" << std::endl;
}


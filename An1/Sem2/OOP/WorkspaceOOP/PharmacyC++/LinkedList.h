//
//  LinkedList.h
//  PharmacyC++
//
//  Created by Mihai Costea on 20/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____LinkedList__
#define __PharmacyC____LinkedList__

#include <iostream>
#include <vector>

using namespace std;

template<class T>
struct Node {
    Node<T>* next;
    T data;
};

template <class T>
class LinkedList {

private:
    vector<Node<T>> _elements;
public:
    LinkedList();
    Node<T>* getFirstElement();
    void addElement(T data);
    void removeElement(T data);
};

#endif /* defined(__PharmacyC____LinkedList__) */

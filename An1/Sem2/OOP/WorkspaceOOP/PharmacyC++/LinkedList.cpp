//
//  LinkedList.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 20/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "LinkedList.h"

template <class T>
LinkedList<T>::LinkedList() {
    if (this->_elements.size() == 0) this->_elements.push_back(NULL);
    else this->_elements[0] = NULL;
}

template <class T>
Node<T>* LinkedList<T>::getFirstElement() {
    return this->_elements[0];
}

template <class T>
void LinkedList<T>::addElement(T data) {
    Node<T> node;
    int position = this->_elements.size();
    node.data = data;
    node.next = &this->_elements[position+1];
    this->_elements.erase(this->_elements.end()-1);
    this->_elements.push_back(node);
    this->_elements.push_back(NULL);
}

template <class T>
void LinkedList<T>::removeElement(T data) {
    Node<T> node = this->getFirstElement();
    for (int i=0; i<this->_elements.size(); i++){
        if (this->_elements[i].data == data){
            this->_elements.erase(this->_elements.end()+i);
        }
    }
}
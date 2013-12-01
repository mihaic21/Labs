#ifndef NODE_H_
#define NODE_H_

#include <iostream>

template<typename T> class List;

template <typename T> class Node{
private:
	T data;
	Node* next;
public:
	friend class List<T>;
	Node(T data){
		this->data=data;
		next=NULL;
	}

	Node* getNext(){
		return this->next;
	}

	T getData(){
		return this->data;
	}

};

#endif /* NODE_H_ */

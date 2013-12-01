#ifndef LIST_H_
#define LIST_H_

#include "Node.h"

template<typename T> class List
{
private:
	Node<T>* start;
public:
	List(){
		this->start=NULL;
	}
	~List(){
		if(!isEmpty()){
			Node<T>* current=this->start;
			Node<T>* temp;
			while(current!=NULL){
				temp=current;
				current=current->getNext();
				delete temp;
			}
		}
	}

	bool isEmpty(){
		return start==NULL;
	}

	void push_back(T data){
		if (isEmpty()){
			Node<T>* head=new Node<T>(data);
			this->start=head;
		}
		else{
			Node<T>* nn=new Node<T>(data);
			Node<T>* current=this->start;
			Node<T>* temp;
			while(current!=NULL){
				temp=current;
				current=current->getNext();
			}
			temp->next=nn;
		}
	}

	void remove(T data){
		if (isEmpty())
			return;
		Node<T>* current=this->start;
		if (current->getData()==data){
			this->start=current->next;
			delete current;
			return;
		}
		Node<T>* prev=current;
		while(current->next!=NULL){
			if (current->getData()==data){
				prev->next=current->next;
				delete current;
				return;
			}
			prev=current;
			current=current->next;
		}
		if(current->getData()==data){
			prev->next=NULL;
			delete current;
		}
	}

	void update(T data, T newdata){
		if (isEmpty())
			return;
		Node<T>* current=this->start;
		while(current!=NULL){
			if (current->getData()==data){
				current->data=newdata;
				return;
			}
			current=current->next;
		}
	}

	T at(int index)const{
		Node<T>* current=this->start;
		while(index>0 and current!=NULL){
			current=current->next;
			index--;
		}
		if(current==NULL)
			return NULL;
		return current->getData();
	}

	int size()const{
		Node<T>* current=this->start;
		int size=0;
		if(current!=NULL){
			while(current!=NULL){
				current=current->getNext();
				size++;
			}
		}
		return size;
	}
};

#endif /* LIST_H_ */

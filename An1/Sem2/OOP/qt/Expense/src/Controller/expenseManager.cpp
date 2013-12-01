/*
 * expenseManager.cpp
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#include "expenseManager.h"

#include <string>
using namespace std;

ExpenseManager::ExpenseManager(ExpenseRepository* repo) {
	this->repo = repo;
}

void ExpenseManager::saveExpense(string type, int amount) {
	repo->addExpense(type, amount);
}

typedef int (*comparefunction)(void*, void*);

int compareTA(Expense* e1, Expense* e2) {
	string q1 = e1->getType();
	string q2 = e2->getType();

	if (q1 < q2) {
			 return -1;
		 }
	else {
		if (q1>q2) {
			return 1;
		}
		return 0;
	}
}

LinkedList<Expense*>* ExpenseManager::sortByType() {
	LinkedList<Expense*>* all=repo->getAll();
	Iterator<Expense*> i = all->begin();
	Iterator<Expense*> j = all->begin();
	++j;
	Iterator<Expense*> end = all->end();

	bool sorted = false;
		do {
			sorted = true;
			i = all->begin();
			while (i.getNode()->getNext()!=0) {
				j = i;
				++j;
				if (compareTA(*i, *j)==1) {
					sorted = false;
					i.getNode()->swapContent(j.getNode());
				}
				++i;
			}

		} while(not sorted);
	return all;
}

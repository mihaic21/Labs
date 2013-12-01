/*
 * expenseManager.h
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#ifndef EXPENSEMANAGER_H_
#define EXPENSEMANAGER_H_

#include "../Repository/expenseRepository.h"

class ExpenseManager {
private:
	ExpenseRepository* repo;

public:
	ExpenseManager(ExpenseRepository* repo);

	void saveExpense(string type, int amount);
	LinkedList<Expense*>* sortByType();
};


#endif /* EXPENSEMANAGER_H_ */

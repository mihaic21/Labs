/*
 * expenseRepository.h
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#ifndef EXPENSEREPOSITORY_H_
#define EXPENSEREPOSITORY_H_

#include "../Domain/expense.h"
#include "../Utils/list.h"

class ExpenseRepository {

private:
	LinkedList<Expense*>* expenseList;
	string filename;

public:
	ExpenseRepository(string filename);

	void writeToFile();
	void addExpense(string type, int amount);
	Expense* getByType(string type);
	LinkedList<Expense*>* getAll();
};

#endif /* EXPENSEREPOSITORY_H_ */

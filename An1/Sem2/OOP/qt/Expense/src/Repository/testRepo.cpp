/*
 * testRepo.cpp
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#include "testRepository.h"
#include "expenseRepository.h"

#include <iostream>
using namespace std;

void testRepo() {
	ExpenseRepository* repo = new ExpenseRepository("expenses.txt");

	cout << "Expense Repo successful!";
}



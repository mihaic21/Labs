/*
 * testExpense.cpp
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#include "testExpense.h"
#include "expense.h"

#include <iostream>
#include <assert.h>
using namespace std;

void testExpense() {
	Expense* e = new Expense(1, "gas", 20);

	assert (e->getType() == "gas");

	cout << "Expense successful!" << endl;
}



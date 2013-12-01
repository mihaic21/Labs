/*
 * expense.h
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#ifndef EXPENSE_H_
#define EXPENSE_H_

#include <string>
using namespace std;

class Expense {

private:
	int id;
	string type;
	int amount;

public:
	Expense(int id, string type, int amount) {
		this->id = id;
		this->type = type;
		if(amount < 0) {
			throw ("quantity must be positive");
		}
		this->amount = amount;


	}

	//getters
	int getId() { return this->id; }
	string getType() { return this->type; }
	int getAmount() { return this->amount; }

	//setters
	void setAmount(int amount) {
		if(amount < 0) {
			throw ("quantity must be positive");
		}
		this->amount = amount;
	}
};


#endif /* EXPENSE_H_ */

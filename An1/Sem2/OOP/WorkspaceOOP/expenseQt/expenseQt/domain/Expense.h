#ifndef EXPENSE_H_
#define EXPENSE_H_

#include <iostream>
#include <string.h>
#include <iomanip>
#include <sstream>
using namespace std;

class Expense
{
private:
	int id;
	int day;
	int amount;
	string type;

public:
	//Constructors
	Expense();
	Expense(int id, int day, int amount, string type);
	Expense(const Expense& e);

	//Redefine assignment operator
	Expense& operator=(const Expense& e);

    string print();
	//Destructor
	~Expense();

	//Getters
	//Expense ID
	int getId()const;

	//Day
	int getDay()const;

	//Amount
	int getAmount()const;

	//Type
	string getType()const;


	//setters
	//Expense ID
	void setId(int id);

	//Day
	void setDay(int amount);

	//Amount
	void setAmount(int amount);

	//Type
	void setType(string type);

	/*
	 * Redefine >> operator
	 */
	friend istream& operator>>(istream& is, Expense&);

	/*
	 * Redefine << operator
	 */
	friend ostream& operator<<(ostream& os, const Expense&);
};

#endif /* Expense_H_ */

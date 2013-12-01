
#ifndef UTILS_H_
#define UTILS_H_

#include <iostream>
#include <vector>
#include <string>
#include "../domain/Expense.h"
#include "../domain/Exceptions.h"
using namespace std;

vector<string> tokenize(const std::string& s, char delimiter);
bool compareByAmountA( Expense* p1, Expense* p2);
bool compareByAmountD( Expense* p1, Expense* p2);
bool compareByTypeA(Expense* p1, Expense* p2);
bool compareByTypeD(Expense* p1, Expense* p2);
int convert(string str) throw (IException);
int convertf(string str) throw (IException);

#endif /* UTILS_H_ */

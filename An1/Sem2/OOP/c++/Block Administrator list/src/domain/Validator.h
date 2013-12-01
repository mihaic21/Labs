#ifndef VALIDATOR_H_
#define VALIDATOR_H_

#include "Expense.h"
#include <iostream>
#include "Exceptions.h"
using namespace std;


class Validator
{
public:
	void validate(const Expense&)throw(RepositoryException);
};

#endif /* VALIDATOR_H_ */

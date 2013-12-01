#ifndef CONTROLLER_H_
#define CONTROLLER_H_

#include "../repository/MemRepository.h"
#include "../domain/Validator.h"

class Controller{
private:
	MemRepository* repo;
	Validator val;
public:

	Controller(MemRepository* repo);

	void add(const Expense&)throw (RepositoryException);

	void remove(const int id);

	void update(const int id, Expense&)throw (RepositoryException);

	int size() const;

	vector<Expense*> getAll() const;

	vector<Expense*> sortByAmountA();

	vector<Expense*> sortByAmountD();

	vector<Expense*> sortByTypeA();

	vector<Expense*> sortByTypeD();

	vector<Expense*> filterByAmount(int amount);

	vector<Expense*> filterByDay(int day);

	~Controller();
};


#endif /* CONTROLLER_H_ */

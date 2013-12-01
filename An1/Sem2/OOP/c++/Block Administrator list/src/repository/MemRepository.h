#ifndef MEMREPOSITORY_H_
#define MEMREPOSITORY_H_

#include "../domain/Expense.h"
#include "../utils/utils.h"
#include "List.h"
#include <algorithm>

using namespace std;

class MemRepository{
protected:
	List<Expense*> repo;
public:
	virtual Expense* findById (int id)const;
	virtual void add(const Expense&) throw (RepositoryException);
	virtual int size()const;
	virtual ~MemRepository();
	virtual vector<Expense*> getAll() const;
	virtual void remove(const int id)throw (RepositoryException);
	virtual void update(const int id, Expense&)throw (RepositoryException);
	virtual vector<Expense*> sortByAmountA();
	virtual vector<Expense*> sortByAmountD();
	virtual vector<Expense*> sortByTypeA();
	virtual vector<Expense*> sortByTypeD();
	virtual vector<Expense*> filterByAmount(int amount);
	virtual vector<Expense*> filterByDay(int day);
};

#endif /* MEMREPOSITORY_H_ */

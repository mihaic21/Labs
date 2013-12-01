#ifndef MREPOSITORY_H_
#define MREPOSITORY_H_

#include "../domain/Expense.h"
#include "../utils/utils.h"
#include <vector>
#include <algorithm>

using namespace std;

class MemRepository{
protected:
	vector<Expense*> repo;
public:
	virtual Expense* findById (int id)const;
	virtual void add(const Expense& p) throw (RepositoryException);
	virtual int size()const;
	virtual ~MemRepository();
	virtual vector<Expense*> getAll() const;
	virtual void remove(const int id)throw (RepositoryException);
	virtual void update(const int id, Expense& pn)throw (RepositoryException);
	virtual vector<Expense*> sortByAmountA();
	virtual vector<Expense*> sortByAmountD();
	virtual vector<Expense*> sortByTypeA();
	virtual vector<Expense*> sortByTypeD();
	virtual vector<Expense*> filterByAmount(int amount);
	virtual vector<Expense*> filterByDay(int day);
};

#endif /* MREPOSITORY_H_ */

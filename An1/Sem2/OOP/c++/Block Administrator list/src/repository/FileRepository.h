#ifndef FILEREPOSITORY_H_
#define FILEPOSITORY_H_

#include "../domain/Expense.h"
#include "MemRepository.h"

class FileRepository: public MemRepository
{
private:
	string filename;
public:
	FileRepository(const string filename);
	void add(const Expense& p)throw (RepositoryException);
	void remove(const int id)throw (RepositoryException);
	void update(const int id, Expense& pn)throw (RepositoryException);
	void loadFromFile();
	void saveToFile();
	virtual ~FileRepository(){};
};

#endif /* FILEREPOSITORY_H_ */

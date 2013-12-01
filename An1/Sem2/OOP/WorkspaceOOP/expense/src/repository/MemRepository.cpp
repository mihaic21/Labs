#include "MemRepository.h"

int MemRepository::size()const{
	return this->repo.size();
}

Expense* MemRepository::findById(int id)const{
	for (int i=0; i<this->size(); i++){
		Expense* e = this->repo.at(i);
		if(e!=NULL)
			if(e->getId() == id)
				return e;
	}
	return NULL;
}

void MemRepository::add(const Expense& e) throw (RepositoryException){
	Expense* e1 = this->findById(e.getId());
	if (e1 == NULL){
		e1 = new Expense(e);
		this->repo.push_back(e1);
	}
	else throw RepositoryException("Duplicated id!\n");
}

vector<Expense*> MemRepository::getAll()const{
	vector<Expense*> copy;
	for(unsigned i = 0; i < this->repo.size(); i++){
		copy.push_back(this->repo.at(i));
	}
	return copy;
}

MemRepository::~MemRepository(){
	for (unsigned i = 0; i < this->repo.size(); i++){
		Expense* e = this->repo.at(i);
		if(e != NULL){
			delete e;
		}
	}
}

void MemRepository::remove(const int id) throw (RepositoryException){
	Expense* e1;
	for (unsigned i = 0; i < this->repo.size(); i++){
		e1 = this->repo.at(i);
		if(e1->getId() == id){
			this->repo.erase(this->repo.begin()+i);
			return;
		}
	}
	throw RepositoryException("No Expense with this id!\n");
}

void MemRepository::update(const int id, Expense& e1) throw (RepositoryException){
	Expense* e;
	Expense* e2 = new Expense(e1);
	for (unsigned i = 0; i < this->repo.size(); i++){
		e = this->repo.at(i);
			if(e->getId() == id){
				this->repo.at(i) = e2;
				return;
			}
	}
	throw RepositoryException("No Expense with this id!\n");
}

vector<Expense*> MemRepository::sortByAmountA(){
	vector<Expense*> s;
	s = this->getAll();
	sort(s.begin(), s.end(), compareByAmountA);
	return  s;
}


vector<Expense*> MemRepository::sortByAmountD(){
	vector<Expense*> s;
	s = this->getAll();
	sort(s.begin(), s.end(), compareByAmountD);
	return  s;
}

vector<Expense*> MemRepository::sortByTypeA(){
	vector<Expense*> s;
	s = this->getAll();
	sort(s.begin(), s.end(), compareByTypeA);
	return  s;
}

vector<Expense*> MemRepository::sortByTypeD(){
	vector<Expense*> s;
	s = this->getAll();
	sort(s.begin(), s.end(), compareByTypeD);
	return  s;
}

vector<Expense*> MemRepository::filterByAmount(int amount){
	vector<Expense*> all = this->getAll();
	vector<Expense*> found;
	for(int i = 0; i < this->size(); i++)
		if (all[i]->getAmount() == amount)
			found.push_back(all[i]);
	return found;
}

vector<Expense*> MemRepository::filterByDay(int day){
	vector<Expense*> all = this->getAll();
	vector<Expense*> found;
	for(int i = 0; i < this->size(); i++)
		if (all[i]->getDay() == day)
			found.push_back(all[i]);
	return found;
}

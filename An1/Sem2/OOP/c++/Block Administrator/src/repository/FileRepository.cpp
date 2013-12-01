#include "FileRepository.h"
#include<fstream>
#include<stdlib.h>
#include "../utils/utils.h"

using namespace std;

FileRepository::FileRepository(const string filename) : MemRepository(){
	this->filename = filename;
	this->loadFromFile();
}

void FileRepository::add(const Expense& p) throw (RepositoryException){
	MemRepository::add(p);
	this->saveToFile();
}

void FileRepository::saveToFile(){
	ofstream f(this->filename.c_str());
	for(int i = 0; i < this->size(); i++){
		Expense* p = this->repo.at(i);
		if(p != NULL)
			f<<*p;
	}
	f.close();
}

void FileRepository::loadFromFile(){
	ifstream f(this->filename.c_str());
	if(!f.is_open())
		return;
	string line = "";
	while(f.good()){
		getline(f, line);
		vector<string> attrs = tokenize(line,'|');
		if(attrs.size() == 4){
			Expense p;
			int id=atoi(attrs[0].c_str());
			p.setId(id);
			p.setDay(atoi(attrs[1].c_str()));
			p.setAmount(atoi(attrs[2].c_str()));
			p.setType(attrs[3]);
			MemRepository::add(p);
		}
	}
}

void FileRepository::remove(const int id) throw (RepositoryException){
	MemRepository::remove(id);
	this->saveToFile();
}

void FileRepository::update(const int id, Expense& p) throw (RepositoryException){
	MemRepository::update(id,p);
	this->saveToFile();
}

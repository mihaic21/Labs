/*
 * expenseRepository.cpp
 *
 *  Created on: 22.06.2013
 *      Author: Ioana
 */

#include "expenseRepository.h"

#include <iostream>
#include <fstream>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <cstdio>
using namespace std;

ExpenseRepository::ExpenseRepository(string filename) {

	expenseList = new LinkedList<Expense*>();

	ifstream file (filename.c_str());

	int i = 5;
	while (file.good()) {
		string word, type;

		getline(file, type, ':');
		getline(file, word);
		int amount = atoi(word.c_str());

		int id = expenseList->getSize() + 1;

		Expense* q = new Expense(id, type, amount);
		expenseList->add(q);
		i--;
	}
	file.close();
}

void ExpenseRepository::addExpense(string type, int amount) {
	Expense* aux = getByType(type);
	if (aux != NULL)
	{
		int newQuantity = amount + aux->getAmount();
		aux->setAmount(newQuantity);
		writeToFile();
	}
	else{
		int id = expenseList->getSize() + 1;
		Expense* q = new Expense(id, type, amount);
		expenseList->add(q);
		writeToFile();
	}
}

Expense* ExpenseRepository::getByType(string type) {
	if (expenseList->getSize() == 0)
		return NULL;

	LinkedList<Expense*>::It iter = expenseList->begin();
	LinkedList<Expense*>::It end = expenseList->end();

	while (iter != end){
		if ((*iter)->getType() == type){
			return *iter;
		}
		++iter;
	}
	return NULL;
}

LinkedList <Expense*>* ExpenseRepository::getAll(){
	return this->expenseList;
}

void ExpenseRepository::writeToFile() {
	ofstream file;
	file.open("expense.txt");	//i.e. all content is discarded, I rewrite everything

	LinkedList<Expense*>* all = getAll();
	Iterator<Expense*> iter = all->begin();
	Iterator<Expense*> end = all->end();

	while (iter != end){
		file << (*iter)->getType() << ":" << (*iter)->getAmount() << endl;
		++iter;
	}

	file.close();

}


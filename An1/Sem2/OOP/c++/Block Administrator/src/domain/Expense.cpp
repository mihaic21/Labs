/*
 * Expense.cpp
 *
 *  Created on: May 21, 2013
 *      Author: mihai
 */


#include "Expense.h"
#include "../utils/utils.h"


Expense::Expense(){
	this->id = 0;
	this->type = "";
	this->amount = 0;
	this->day = 0;
}


Expense::Expense(int id, int day, int amount, string type){
	this->id = id;
	this->type = type;
	this->amount = amount;
	this->day = day;
}


Expense::Expense(const Expense& e){
	this->id = e.getId();
	this->type = e.getType();
	this->amount = e.getAmount();
	this->day = e.getDay();
}


Expense& Expense::operator=(const Expense& e){
	if(this==&e){
		return* this;
	}
	this->id = e.getId();
	this->type = e.getType();
	this->amount = e.getAmount();
	this->day = e.getDay();
	return* this;
}


Expense::~Expense(){
}


int Expense::getId()const{
	return this->id;
}

int Expense::getAmount()const{
	return this->amount;
}

int Expense::getDay()const{
	return this->day;
}

string Expense::getType()const{
	return this->type;
}


void Expense::setId(int id){
	this->id = id;
}

void Expense::setType(string type){
	this->type = type;
}


void Expense::setAmount(int amount){
	this->amount = amount;
}

void Expense::setDay(int day){
	this->day = day;
}


istream& operator>>(istream& is, Expense& e){
	int id, day, amount;
	string type;
	string read;

	cout<<"ID: ";
	is>>read;
	id = convert(read);
	cout<<"Day: ";
	is>>read;
	day = convert(read);
	cout<<"Amount: ";
	is>>read;
	amount = convert(read);
	cout<<"Type of expense:\n"<<" - housekeeping\n"<<" - food\n"<<" - transport\n"<<" - clothing\n";
	cout<<" - telephone\n"<<" - others\n>";
	is>>type;

	e.setId(id);
	e.setType(type);
	e.setDay(day);
	e.setAmount(amount);
	return is;
}



ostream& operator<<(ostream& os, const Expense& e){
	os<<setw(4)<<e.getId()<<'|'<<setw(10)<<e.getDay()<<'|'<<setw(10)<<e.getAmount()<<'|'<<setw(15)<<e.getType()
			<<"|\n";
	return os;
}


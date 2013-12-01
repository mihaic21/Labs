/*
 * Exceptions.h
 *
 *  Created on: May 21, 2013
 *      Author: mihai
 */

#ifndef EXCEPTIONS_H_
#define EXCEPTIONS_H_

#include<iostream>
using namespace std;

class MyException : public exception
{
private:
	string Msg;

public:
	MyException(const string& msg) : Msg(msg) {}
	virtual ~MyException() throw() {}

	string getMsg() const
	{
		return this->Msg;
	}

	virtual const char* what() const throw()
	{
		return this->Msg.c_str();
	}
};

class ExpenseException : public MyException
{
public:
	ExpenseException(const string& msg) : MyException(msg) {}
};

class RepositoryException : public MyException
{
public:
	RepositoryException(const string& msg) : MyException(msg) {}
};

class IException : public MyException
{
public:
	IException(const string& msg) : MyException(msg) {}
};

#endif /* EXCEPTIONS_H_ */

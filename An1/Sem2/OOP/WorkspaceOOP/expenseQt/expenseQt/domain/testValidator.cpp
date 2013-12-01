#include "testValidator.h"

void testValidator(){
	Validator v;
	string errors="";
	Expense e(1,2,3,"food");
	try{
		v.validate(e);
	}
	catch(RepositoryException& s1)
	{
		errors=s1.getMsg();
	}
	assert(errors=="");

	Expense e1(1,33,44,"food");
	try{
		v.validate(e1);
	}
	catch(RepositoryException& s)
	{
		errors=s.getMsg();
	}
	assert (errors!="");

	errors="";
	Expense e2(1,23,44,"pizza");
	try{
		v.validate(e2);
	}
	catch(RepositoryException& s2)
	{
		errors=s2.getMsg();
	}
	assert (errors!="");

	errors="";
	Expense e3(1,2,-3,"others");
	try{
		v.validate(e3);
	}
	catch(RepositoryException& s3)
	{
		errors=s3.getMsg();
	}
	assert (errors!="");
}

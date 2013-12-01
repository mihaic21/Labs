#include "testController.h"

void testController(){
	MemRepository* repo=new MemRepository();
	Controller c(repo);
	string errors="";
	Expense p(1, 21, 92, "clothing");
	c.add(p);
	assert(c.size()==1);
	Expense p1(2, 23, 440, "telephone");
	c.add(p1);
	assert(c.size()==2);
	Expense p2(3, 2, 9, "pizza");
	try{
		c.add(p2);
	}
	catch(RepositoryException& s)
	{
		errors+=s.getMsg();
	}
	assert(c.size()==2);
	assert(errors!="");

	errors="";
	Expense p3(3, 34, 29, "others");
	try{
		c.add(p2);
	}
	catch(RepositoryException& s)
	{
		errors+=s.getMsg();
	}
	assert(c.size()==2);
	assert(errors!="");

	Expense p4(1, 23, 440, "others");
	c.update(1,p4);
	vector<Expense*> all=c.getAll();
	assert(all.at(0)->getDay()==23);
	assert(c.size()==2);
	errors="";
	try{
		c.update(1,p3);
	}
	catch (RepositoryException& s1)
	{
		errors+=s1.getMsg();
	}
	assert(errors!="");

	c.remove(1);
	assert (c.size()==1);
	errors="";
	try{
		c.remove(7);
	}
	catch (RepositoryException& s1)
	{
		errors+=s1.getMsg();
	}
	assert(errors!="");
	assert (c.size()==1);

	c.add(p);
	all=c.filterByDay(23);
	assert(all.size()==1);
	all=c.filterByAmount(440);
	assert(all.size()==1);

	all=c.sortByAmountA();
	assert(all.at(0)->getId()==1);
	all=c.sortByAmountD();
	assert(all.at(0)->getId()==2);
	all=c.sortByTypeA();
	assert(all.at(0)->getId()==1);
	all=c.sortByTypeD();
	assert(all.at(0)->getId()==2);
}

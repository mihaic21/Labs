#include "TestExpense.h"
#include<assert.h>

void testExpense(){
	Expense e(1, 2, 44, "others");
	assert (e.getId()==1);
	assert (e.getType()=="others");
	assert (e.getDay()==2);
	assert (e.getAmount()==44);

	e.setType("food");
	e.setAmount(55);
	e.setDay(3);
	e.setId(2);

	assert(e.getId()==2);
	assert(e.getDay()==3);
	assert (e.getType()=="food");
	assert (e.getAmount()==55);

	Expense e1;
	e1=e;
	assert(e1.getId()==2);
	assert(e1.getDay()==3);
	assert (e1.getType()=="food");
	assert (e1.getAmount()==55);
}

/*
 * tests.c
 *
 *  Created on: 26 Mar 2013
 *      Author: pcnc
 */

#include "apExpense.h"
#include "stdlib.h"
#include "assert.h"
#include "string.h"

void tests(){
	apExpense* a1 = malloc(sizeof(apExpense));
	init(a1,22,200,"heating");
	assert(strcmp("heating", a1->type) == 0);
	assert(a1->apNumber == 22);
	assert(a1->amount == 200);

	setAmount(a1, 99);
	assert(a1->amount == 99);

	setID(a1,44);
	assert(a1->id == 44);


	apExpense* a2 = malloc(sizeof(apExpense));
	init(a2,120,40,"gas");
	assert(a2->id != a1->id);

	assert(cmpTypes(a1,a2)>0);
	assert(cmpAmounts(a1,a2) == 0);

	free(a1);
	free(a2);
}

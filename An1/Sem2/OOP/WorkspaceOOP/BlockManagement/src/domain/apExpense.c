/*
 * apExpense.c
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */
#include "apExpense.h"
#include "../utils/utils.h"
#include <stdlib.h>
static int ID = 1;

int returnID(){
	return ID;
}

void init(apExpense* a, int apNumber, int amount, char* type){
	/*
	 * Initialises an apExpense object
	 */
	a->id = ID;
	a->apNumber = apNumber;
	a->amount = amount;
	a->type = copyString(type);
}

void setAmount(apExpense* a, int value){
	/*
	 * sets a given amount to the apExpense object
	 */
	a->amount = value;
}

void setID(apExpense* a, int value){
	a->id = value;
}

int getAmount(apExpense* a){
	/*
	 * retrieves the amount of the apExpense object
	 */
	return a->amount;
}

void setType(apExpense* a, char* type){
	/*
	 * sets a given type to the apExpense object
	 */
	a->type = copyString(type);
}

int cmpTypes(apExpense* a1, apExpense* a2){
	/*
	 * compares types of the given apExpense objects
	 */
	return strcmp(a1->type, a2->type);
}

int cmpAmounts(apExpense* a1, apExpense* a2){
	/*
	 * compares amounts of the given apExpense objects
	 */
	return a1->amount < a2->amount;
}

int cmpAmountsASC(apExpense* a1, apExpense* a2){
	/*
	 * compares amounts of the given apExpense objects
	 */
	return a1->amount > a2->amount;
}

char* getType(apExpense* a){
	/*
	 * retrieves type of the apExpense object
	 */
	return a->type;
}

int getApNumber(apExpense* a){
	/*
	 * retrieves the apNumber(apartment number) of the apExpense object
	 */
	return a->apNumber;
}

apExpense* copyExpense(apExpense* a){
	/*
	 * returns a new apExpense object, after copying its properties
	 */
	apExpense* temp = malloc(sizeof(apExpense));
	temp->id = ID++;
	temp->amount = a->amount;
	temp->apNumber = a->apNumber;
	temp->type = copyString(a->type);

	return temp;
}

void delExpense(apExpense* a){
	free(a->type);
	free(a);
	a = NULL;
}

/*
 * controller.c
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */
#include <stdlib.h>
#include "../utils/vector.h"
#include "../domain/apExpense.h"
#include "../repository/repository.h"

void addExpenseContr(int apNo, int amount, char* expenseType){
	/*
	 * adds a new apExpense entry to the repository
	 */
	apExpense* a = malloc(sizeof(apExpense));
	init(a, apNo, amount, expenseType);
	addExpense(a);
}

apExpense** getAllContr(int* count){
	/*
	 * returns all the entries of the repository
	 */
	return getAll(count);
}

void deleteEntryContr(int choice){
	/*
	 * deletes a certain entry in the repository
	 */
	deleteExpense(choice);
}

int getEntryNumber(){
	/*
	 * gets total number of entries in the repository
	 */
	return getEntryNo();
}

void setAmountContr(int id, int amount){
	/*
	 * sets a new amount of an object in the repository
	 */
	setAmountRepo(id, amount);
}

void setTypeContr(int id, char* type){
	/*
	 * sets a new type of an object in the repository
	 */
	setTypeRepo(id, type);
}

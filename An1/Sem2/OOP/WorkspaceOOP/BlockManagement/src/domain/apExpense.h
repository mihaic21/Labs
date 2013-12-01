/*
 * apExpense.h
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */

#ifndef APEXPENSE_H_
#define APEXPENSE_H_

typedef struct {
	int id;
	int apNumber;
	int amount;
	char* type;
} apExpense;

void init(apExpense*, int apNumber, int amount, char* type);

void setAmount(apExpense*, int value);
int getAmount(apExpense* );

void setType(apExpense*, char* type);
char* getType(apExpense*);

void setID(apExpense*, int);
int getApNumber(apExpense*);

apExpense* copyExpense(apExpense*);
void delExpense(apExpense*);

int cmpTypes(apExpense*, apExpense*);
int cmpAmounts(apExpense*, apExpense*);
int cmpAmountsASC(apExpense*, apExpense*);

#endif /* APEXPENSE_H_ */

/*
 * repository.h
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */

#ifndef REPOSITORY_H_
#define REPOSITORY_H_

apExpense** getAll(int* count);
void addExpense(apExpense*);
void remExpense(apExpense*);
void readFromFile();
void writeToFile();
void destroyRepo();
int getEntryNo();
void setAmountRepo(int, int);
void setTypeRepo(int, char*);
char* writeExpense(apExpense* a, int id);
void deleteExpense(int id);

#endif /* REPOSITORY_H_ */

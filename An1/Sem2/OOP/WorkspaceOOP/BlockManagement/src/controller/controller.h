/*
 * controller.h
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */

void addExpenseContr(int apNo, int amount, char* expenseType);
apExpense** getAllContr(int* count);
void deleteEntryContr(int choice);
int getEntryNumber();
void setAmountContr(int id, int amount);
void setTypeContr(int id, char* type);

/*
 * ui.c
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "../domain/apExpense.h"
#include "../repository/repository.h"
#include "../utils/utils.h"
#include "../controller/controller.h"

void printMenu(){
	/*
	 * Prints main application menu
	 */
	printf("Welcome Michael, you have the following options:\n");
	printf("1. See current expenses\n");
	printf("2. Add new expense\n");
	printf("3. Modify existing expense\n");
	printf("4. Delete existing expense\n");
	printf("5. Filter expenses by\n");
	printf("6. Sort expenses by\n");
	printf("0. Exit\n");
	printf("Select option: \n");
}

void expenseMenu() {
	/*
	 * Prints expense type menu
	 */
	printf("Select expense type:\n");
	printf("1. water\n");
	printf("2. heating\n");
	printf("3. illuminating\n");
	printf("4. gas\n");
	printf("5. others\n");
}

void printEntries(){
	/*
	 * Prints all entries from the repository
	 */
	int* count=malloc(sizeof(int));
	*count = 0; int i;
	printf("%4s %6s %8s %15s\n","ID","ApNo", "Amount", "Type");
	//apExpense** temp = getAll(count);
	apExpense** temp = getAllContr(count);
	for(i=0; i < *count; i++){
		printf("%s",writeExpense(temp[i],i+1));
	}
	free(count);
}

void filterbyApNo(){
	/*
	 * Filters by given apartment number
	 */
	printEntries();
	printf("Input apartment number: ");
	int apNo;
	scanf("%d", &apNo);

	int *count=malloc(sizeof(int)),i,j=0;
	*count=0;
	apExpense** tempV = getAllContr(count);

	if(*count != 0){
		printf("%4s %6s %8s %15s\n","ID","ApNo", "Amount", "Type");
		for(i=0; i < *count; i++){
			if(tempV[i]->apNumber == apNo){
				j++;
				printf("%s", writeExpense(tempV[i],j));
			}
		}
	} else
		printf("There are no entries that match your criteria");
	free(count);
}

void filterAsc(int amount){
	/*
	 * Filters entries by given sum, if their amount property is greater or equal than the sum
	 */
	int *count=malloc(sizeof(int)),j=0;
	*count=0;
	int i;
	apExpense** tempV = getAllContr(count);

	if(*count != 0){
		printf("%4s %6s %8s %15s\n","ID","ApNo", "Amount", "Type");
		for(i=0; i < *count; i++){
			if(tempV[i]->amount >= amount){
				j++;
				printf("%s", writeExpense(tempV[i],j));
			}
		}
	} else
		printf("There are no entries that match your criteria");
	free(count);
}

void filterDesc(int amount){
	/*
	 * Filters entries by given sum, if their amount property is lesser than the sum
	 */
	int *count=malloc(sizeof(int)),j=0;
	*count=0;
	int i;
	apExpense** tempV = getAllContr(count);

	if(*count != 0){
		printf("%4s %6s %8s %15s\n","ID","ApNo", "Amount", "Type");
		for(i=0; i < *count; i++){
			if(tempV[i]->amount < amount){
				j++;
				printf("%s", writeExpense(tempV[i],j));
			}
		}
	} else
		printf("There are no entries that match your criteria");
	free(count);
}

void filterByMoney(){
	/*
	 * Filtering by sum menu
	 */
	int amount, moreless=0;
	printf("Input amount of money: ");
	scanf("%d", &amount);
	printf("Filter by less or more than %d?\n", amount);
	printf("1. More or equal\n2. Less\n");
	while(moreless == 0){
		scanf("%d", &moreless);
		switch(moreless){
			case 1:
				filterAsc(amount);
				break;
			case 2:
				filterDesc(amount);
				break;
			case -1:
				return;
			default:
				moreless = 0;
				printf("Value not valid, try again or input '-1' to cancel");
				break;
		}
	}
}

void filterBy(){
	/*
	 * Filtering menu
	 */
	printf("Select filtering criteria: \n");
	printf("1. amount of money\n");
	printf("2. apartment number\n");
	int choice;
	scanf("%d", &choice);
	switch(choice){
		case 1:
			filterByMoney();
			break;
		case 2:
			filterbyApNo();
			break;
		default:
			printf("Option doesn't exist, try again.\n");
	}
}

void addEntryMenu(){
	/*
	 * Provides the opportunity to add a new entry
	 */
	int apNo, amount, expenseNo=0;
	char expenseType[15];
	strcpy(expenseType,"");

	printf("Input apartment number: ");
	scanf("%d", &apNo);

	printf("Input expense amount: ");
	scanf("%d", &amount);

	while(strcmp(expenseType,"")==0){
		expenseMenu();

		scanf("%d", &expenseNo);
		if(expenseNo==-1) return;

		switch(expenseNo){
		case 1:
			strcpy(expenseType,"water");
			break;
		case 2:
			strcpy(expenseType,"heating");
			break;
		case 3:
			strcpy(expenseType,"illuminating");
			break;
		case 4:
			strcpy(expenseType,"gas");
			break;
		case 5:
			strcpy(expenseType,"others");
			break;
		default:
			printf("Invalid choice. Select again or input '-1' to cancel attempt.");
		}
	}
	addExpenseContr(apNo,amount,expenseType);
}

void deleteEntryMenu() {
	/*
	 * Provides the opportunity to delete certain a certain entry
	 */
	printEntries();
	printf("Select # to delete, '-1' to cancel attempt: ");
	int n, choice;
	n = getEntryNumber();
	scanf("%d", &choice);
	while ((choice>n || choice<1) || findExpense(choice) == 0){
		if(choice==-1) return;
		printf("Wrong choice, try again. Input '-1' to cancel attempt.\n");
		scanf("%d", &choice);
	}
	deleteEntryContr(choice);
}

void modifyMenu() {
	/*
	 * Provides the possibility to edit values of certain members of the entry
	 */
	printEntries();
	printf("Select # to modify, '-1' to cancel attempt: ");
	int n = getEntryNumber(), choice;
	scanf("%d", &choice);
	while ((choice>n || choice<1) || findExpense(choice) == 0){
		if(choice==-1) return;
		printf("Wrong choice, try again. Input '-1' to cancel attempt.\n");
		scanf("%d", &choice);
	}
	printf("\n1. Modify money amount of entry\n2. Modify expense type\n");
	scanf("%d",&n);
	while(n<1||n>2){
		printf("Option doesn't exist, try again");
		scanf("%d",&n);
	}
	if(n==1){
		printf("Input new amount: ");
		int amount;
		scanf("%d", &amount);
		setAmountContr(choice, amount);
	} else {
		char expenseType[15]; int expenseNo=0;
		while(strcmp(expenseType,"")==0){
			expenseMenu();

			printf("Select new expense: ");
			scanf("%d", &expenseNo);
			if(expenseNo==-1) return;

			switch(expenseNo){
				case 1:
					strcpy(expenseType,"water");
					break;
				case 2:
					strcpy(expenseType,"heating");
					break;
				case 3:
					strcpy(expenseType,"illuminating");
					break;
				case 4:
					strcpy(expenseType,"gas");
					break;
				case 5:
					strcpy(expenseType,"others");
					break;
				default:
					printf("Invalid choice. Select again or input '-1' to cancel attempt.");
			}
		}
		setTypeContr(choice, expenseType);
	}
}

void sortByAmount(){
	/*
	 * Sorts entries by their amount property, ascending or descending
	 */
	int *count=malloc(sizeof(int));
	int i,t=0,n, asc;

	printf("Ascending or descending?\n1. ascending\n2. descending\n");
	scanf("%d", &asc);
	switch(asc){
		case 1:
			break;
		case 2:
			break;
		default:
			printf("Invalid choice, try again.\n");
			return;
	}

	apExpense** temp1 = getAllContr(count);
	apExpense** temp2 = malloc(*count*sizeof(apExpense));
	apExpense* aux = malloc(sizeof(apExpense));
	n=*count;

	for(i=0; i < *count; i++)
		temp2[i] = temp1[i];

	while(t!=1){
		t=1;
		for(i=0; i < n-1; i++){
			if(asc == 1 && cmpAmountsASC(temp2[i],temp2[i+1])){
				t=0;
				aux = temp2[i];
				temp2[i] = temp2[i+1];
				temp2[i+1] = aux;
			}

			if(asc == 2 && cmpAmounts(temp2[i],temp2[i+1])){
				t=0;
				aux = temp2[i];
				temp2[i] = temp2[i+1];
				temp2[i+1] = aux;
			}
		}
	}

	printf("%4s %6s %8s %15s\n","ID","ApNo", "Amount", "Type");
	for(i=0; i < *count; i++){
		printf("%s",writeExpense(temp2[i],i+1));
	}

	aux=NULL;
	free(aux);
	free(temp2);
	free(count);
}

void sortByType(){
	/*
	 * Sorts entries by their type property, ascending or descending
	 */

	int *count=malloc(sizeof(int));
	int i,t=0,n,asc;

	printf("Ascending or descending?\n1. ascending\n2. descending\n");
	scanf("%d", &asc);
	switch(asc){
		case 1:
			break;
		case 2:
			break;
		default:
			printf("Invalid choice, try again.\n");
			return;
	}

	apExpense** temp1 = getAllContr(count);
	apExpense** temp2 = malloc(*count*sizeof(apExpense));
	apExpense* aux = malloc(sizeof(apExpense));

	n=*count;

	for(i=0; i < *count; i++)
		temp2[i] = temp1[i];

	while(t!=1){
		t=1;
		for(i=0; i < n-1; i++){
			if(asc == 1 && cmpTypes(temp2[i],temp2[i+1])>0){
				t=0;
				aux = temp2[i];
				temp2[i] = temp2[i+1];
				temp2[i+1] = aux;
			}

			if(asc == 2 && cmpTypes(temp2[i],temp2[i+1])<0){
				t=0;
				aux = temp2[i];
				temp2[i] = temp2[i+1];
				temp2[i+1] = aux;
			}
		}
	}

	printf("%4s %6s %8s %15s\n","ID","ApNo", "Amount", "Type");
	for(i=0; i < *count; i++){
		printf("%s",writeExpense(temp2[i],i+1));
	}

	aux=NULL;
	free(aux);
	free(temp2);
	free(count);
}



void sortExpensesBy(){
	/*
	 * Expense sorting menu
	 */
	printf("Sort expenses by:\n1. amount\n2. type\n");
	int choice;
	scanf("%d", &choice);
	switch(choice){
		case 1:
			sortByAmount();
			break;
		case 2:
			sortByType();
			break;
		default:
			printf("Option doesn't exist, try again");
	}
}

void runUI(){
	/*
	 * Run general UI
	 */
	while(1){
		printMenu();
		int choice;
		scanf("%d", &choice);
		switch (choice) {
			case 1:
				printEntries();
				break;
			case 2:
				addEntryMenu();
				break;
			case 3:
				modifyMenu();
				break;
			case 4:
				deleteEntryMenu();
				break;
			case 5:
				filterBy();
				break;
			case 6:
				sortExpensesBy();
				break;
			case 0:
				return;
				break;
			default:
				printf("Entry does not exist, try again.\n");
		}
	}
}

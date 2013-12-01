/*
 * repository.c
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */
#include <stdio.h>
#include <stdlib.h>
#include "../utils/vector.h"
#include "../domain/apExpense.h"
#include "repository.h"

char REPO[]="repo.txt";
vector* repo;

void initRepo(){
	/*
	 * initialises repository
	 */
	repo = malloc(sizeof(vector));
	create(repo, remExpense, copyExpense);
}

void destroyRepo(){
	/*
	 * destroys the repository
	 */
	destroy(repo);
}

char* writeExpense(apExpense* a, int id) {
	/*
	 * formats the expense for printing on the screen
	 */
	char* buf = malloc((4 + 4 + 8 + 25 + 1) * sizeof(char));
	sprintf(buf, "%4d %6d %8d %15s\n", id, a->apNumber, a->amount, a->type);
	return buf;
}

void readFromFile(){
	/*
	 * reads entries from file
	 */
	initRepo();

	char type[15]; int id, amount, apNumber;
	FILE* file = fopen(REPO, "r");

	while(fscanf(file, "%d:%d:%d:%s", &id, &apNumber, &amount, type) != EOF){
		apExpense* a = malloc(sizeof(apExpense));
		init(a, apNumber, amount, type);
		add(repo, a);
	}

	fclose(file);
}

char* fileFormat(apExpense* a, int id) {
	/*
	 * formats entries to be written to file
	 */
	char* buf = malloc((4 + 4 + 5 + 25 + 1) * sizeof(char));
	sprintf(buf, "%d:%d:%d:%s\n", id, a->apNumber, a->amount, a->type);
	return buf;
}

void writeToFile(){
	/*
	 * writes entries to file
	 */
	int i = 0;
	FILE* file = fopen(REPO, "w");

	for(i=0; i < repo->len; i++){
		fprintf(file, fileFormat(get(repo, i),i+1));
	}

	fclose(file);
}

apExpense** getAll(int* count){
	/*
	 * returns all entries
	 */
	*count = repo->len;
	return repo->elems;
}

void addExpense(apExpense* a){
	/*
	 * adds expense to repository
	 */
	add(repo, a);
	writeToFile();
}

void deleteExpense(int id){
	/*
	 * deletes entry
	 */
	int i = 0;
	for(i=id-1; i<repo->len-1; i++)
//		repo->elems[i]=repo->elems[i+1];
		repo->elems[i]=copyExpense(repo->elems[i+1]);
	repo->len--;

	remExpense(repo->elems[repo->len-1]);

	writeToFile();
}

void remExpense(apExpense* s){
	/*
	 * clears the memory for an expense
	 */
	free(s->type);
	free(s);
	s=0;
}

int getEntryNo(){
	/*
	 * gets total entry number
	 */
	return repo->len;
}

apExpense* findExpense(int id){
	/*
	 * finds an expense with a given id, returns 0 otherwise
	 */
	if(id<=repo->len)
		return repo->elems[id-1];
	return 0;
}


void setAmountRepo(int id, int amount){
	/*
	 * set amount for an expense
	 */
	apExpense* temp = findExpense(id);
	setAmount(temp, amount);
	writeToFile();
}

void setTypeRepo(int id, char* type){
	/*
	 * sets type for an expense
	 */
	apExpense* temp = findExpense(id);
	setType(temp, type);
	writeToFile();
}

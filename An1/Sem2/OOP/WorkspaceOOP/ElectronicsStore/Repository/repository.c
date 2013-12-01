/*
 * repository.c
 *
 *  Created on: Mar 11, 2013
 *      Author: mihai
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "repository.h"
#include "../Domain/product.h"


Products productRepo;
FILE *repoFile;
char repoFileName[30];

void initRepo(char* filename){
	productRepo.size=0;
	strcpy(repoFileName, filename);
	repoFile = fopen(filename, "r");
	if (repoFile == NULL){
		repoFile = fopen(filename, "w");
		fclose(repoFile);
	} else {
		fclose(repoFile);
	}
	repoFile = fopen(filename, "r");
		char line[1000];
		int lineNumber = 0;
		while (1){
			int result = fscanf(repoFile, "%s", line);
			if (result == EOF){
				productRepo.size = lineNumber;
				break;
			}
			int i, j;
			char buffer[30];
			j=0;
			int count=1;
			for (i=0; i<strlen(line); i++){
				if ((line[i] != '|') && (line[i] != '\n')){
					buffer[j] = line[i];
					j++;
				} else {
					buffer[j] = ' ';
					switch (count){
					case 1: {
						int id;
						sscanf(buffer, "%d", &id);
						productRepo.products[lineNumber].id = id;
						break;
					}
					case 2: {
						char type[30];
						sscanf(buffer, "%s", type);
						type[j]='\0';
						strcpy(productRepo.products[lineNumber].type, type);
						break;
					}
					case 3: {
						char model[30];
						sscanf(buffer, "%s", model);
						model[j]='\0';
						strcpy(productRepo.products[lineNumber].model, model);
						break;
					}
					case 4: {
						char manufacturer[30];
						sscanf(buffer, "%s", manufacturer);
						manufacturer[j]='\0';
						strcpy(productRepo.products[lineNumber].manufacturer, manufacturer);
						break;
					}
					case 5: {
						int price;
						sscanf(buffer, "%d", &price);
						productRepo.products[lineNumber].price = price;
						break;
					}
					case 6: {
						int date;
						sscanf(buffer, "%d", &date);
						productRepo.products[lineNumber].date = date;
						break;
					}
					case 7: {
						int quantity;
						sscanf(buffer, "%d", &quantity);
						productRepo.products[lineNumber].quantity = quantity;
						break;
					}
					} //switch
				strcpy(buffer, "");
				j = 0;

				count++;
				} //else
			} //for
			lineNumber++;
		} //while

	fclose(repoFile);
}

void repoToFile(){
	repoFile = fopen(repoFileName, "w");
	int i;
	for (i=0; i<productRepo.size; i++){
		char outString[100];
		sprintf(outString, "%d|%s|%s|%s|%d|%d|%d|\n", productRepo.products[i].id, productRepo.products[i].type, productRepo.products[i].model,
				productRepo.products[i].manufacturer, productRepo.products[i].price, productRepo.products[i].date, productRepo.products[i].quantity);
		fprintf(repoFile, "%s", outString);
	}
}

int addProductInRepo(char* type, char* model, char* manufacturer, int price, int date, int quantity) {
	int id;
	if (productRepo.size == 0){
		id = 1;
	}
	else{
		id = productRepo.products[productRepo.size - 1].id + 1;
	}
	productRepo.products[productRepo.size].id = id;
	strcpy(productRepo.products[productRepo.size].type, type);
	strcpy(productRepo.products[productRepo.size].model, model);
	strcpy(productRepo.products[productRepo.size].manufacturer, manufacturer);
	productRepo.products[productRepo.size].price = price;
	productRepo.products[productRepo.size].date = date;
	productRepo.products[productRepo.size].quantity = quantity;
	productRepo.size++;

	repoToFile();

	return 0;
}

void getAllProductsFromRepo(Products *result){
	int i;
	for (i=0; i<productRepo.size; i++){
		result->products[i] = productRepo.products[i];
	}
	result->size = productRepo.size;
}

int updatePriceInRepo(int id, int price){
	int i,position;
	int found = 0;
	for (i=0; i<productRepo.size;i++){
		if (productRepo.products[i].id == id){
			found = 1;
			position = i;
			break;
		}
	}

	if (!found){
		return 1;
	}

	productRepo.products[position].price = price;

	repoToFile();

	return 0;
}

int updateQuantityInRepo(int id, int quantity){
	int i,position;
	int found = 0;
	for (i=0; i<productRepo.size;i++){
		if (productRepo.products[i].id == id){
			found = 1;
			position = i;
			break;
		}
	}

	if (!found){
		return 1;
	}

	productRepo.products[position].quantity = quantity;

	repoToFile();

	return 0;
}

int deleteProductInRepo(int id){
	int i,position;
	int found = 0;
	for (i=0; i<productRepo.size;i++){
		if (productRepo.products[i].id == id){
			found = 1;
			position = i;
			break;
		}
	}

	if (!found){
		return 1;
	}

	if (position != productRepo.size-1){
		for (i=position;i<productRepo.size-1;i++){
			productRepo.products[i] = productRepo.products[i+1];
		}
	}
	productRepo.size--;
	repoToFile();

	return 0;
}

void repo_filterByManufacturer(Products* result, char* manufacturer){
	int i;
	int j=0;
	for (i=0; i<productRepo.size; i++){
		if (!strcmp(productRepo.products[i].manufacturer, manufacturer)){
			result->products[j] = productRepo.products[i];
			j++;
		}
	}
	result->size = j;
}

void repo_filterByPrice(Products *result ,int price){
	int i;
	int j=0;
	for (i=0; i<productRepo.size; i++){
		if (productRepo.products[i].price == price){
			result->products[j] = productRepo.products[i];
			j++;
		}
	}
	result->size = j;
}

void repo_filterByDate(Products *result ,int date){
	int i;
	int j=0;
	for (i=0; i<productRepo.size; i++){
		if (productRepo.products[i].date == date) {
			result->products[j] = productRepo.products[i];
			j++;
		}
	}
	result->size = j;
}

void repo_sortByPrice(Products *result, int direction){
	int i;
	int j;
	Product temp;
	for (i=0; i<productRepo.size; i++){
		result->products[i] = productRepo.products[i];
	}
	result->size = productRepo.size;
	for (i=0; i<result->size - 1; i++){
		for (j=i+1; j<result->size; j++){
			if (direction == ASC){
				if (result->products[i].price > result->products[j].price){
					temp = result->products[i];
					result->products[i] = result->products[j];
					result->products[j] = temp;
				}
			}
			else if (direction == DSC){
				if (result->products[i].price < result->products[j].price){
					temp = result->products[i];
					result->products[i] = result->products[j];
					result->products[j] = temp;
				}
			}
		}
	}
}

void repo_sortByDate(Products *result, int direction){
	int i;
	int j;
	Product temp;
	for (i=0; i<productRepo.size; i++){
		result->products[i] = productRepo.products[i];
	}
	result->size = productRepo.size;
	for (i=0; i<result->size - 1; i++){
		for (j=i+1; j<result->size; j++){
			if (direction == ASC){
				if (result->products[i].date > result->products[j].date) {
					temp = result->products[i];
					result->products[i] = result->products[j];
					result->products[j] = temp;
				}
			}
			else{
				if (result->products[i].date < result->products[j].date) {
					temp = result->products[i];
					result->products[i] = result->products[j];
					result->products[j] = temp;
				}
			}
		}
	}
}

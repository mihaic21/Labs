/*
 * console.c
 *
 *  Created on: Mar 11, 2013
 *      Author: mihai
 */
#include "../Controller/controller.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "console.h"
#include "../Repository/repository.h"

static char* menu = "\n [1] Add new product\n [2] Update product price\n [3] Update product quantity\n [4] Remove product\n [5] Filter products\n [6] Sort products\n [7] View all\n\n [x] Exit \n";



int addMenu(){
	int price = 0;
	int quantity = 0;
	int result;
	char* type;
	char* model;
	char* manufacturer;
	int date;
	type = (char*) malloc (30*sizeof(char));
	model = (char*) malloc (30*sizeof(char));
	manufacturer = (char*) malloc (30*sizeof(char));
	date = (char*) malloc (30*sizeof(char));

	printf("Type: ");
	__fpurge(stdin);
	fgets(type,30,stdin);
	type = strtok(type,"\n");

	printf("Model: ");
	__fpurge(stdin);
	fgets(model,30,stdin);
	model = strtok(model,"\n");

	printf("Manufacturer: ");
	__fpurge(stdin);
	fgets(manufacturer,30,stdin);
	manufacturer = strtok(manufacturer,"\n");

	printf("Price: ");
	result = scanf("%d",&price);
	if (!result) {
		printf("Price must be an int value! ");
		return 1;
	}

	printf("Date: ");
	result = scanf("%d",&date);
	if (!result) {
		printf("Date must be an int value! ");
		return 1;
	}
	printf("Quantity: ");
	result = scanf("%d",&quantity);
	if (!result) {
		printf("Quantity must be an int value! ");
		return 1;
	}

	addProduct(type,model,manufacturer,price,date,quantity);
	return 0;
}


void updateProductPriceMenu() {
	int id, newPrice;
	printf("Id of the product: ");
	scanf("%d", &id);
	printf("New price: ");
	scanf("%d", &newPrice);
	int result = updatePrice(id, newPrice);
	if (result == 0) {
		printf("Price was updated!");
	}
	else {
		printf("Unknown id!");
	}
}

void updateProductQuantityMenu() {
	int id, newQuantity;
	printf("Id of the product: ");
	scanf("%d", &id);
	printf("New quantity: ");
	scanf("%d", &newQuantity);
	int result = updateQuantity(id, newQuantity);
	if (result == 0) {
		printf("Quantity was updated!");
	}
	else {
		printf("Unknown id!");
	}
}

void removeProductMenu() {
	int id;
	printf("Id of the product: ");
	scanf("%d", &id);
	int result = deleteProduct(id);
	if (result == 0) {
		printf("Product was deleted successfully!");
	}
	else {
		printf("Unknown id!");
	}
}

void filterMenu() {
	char user_input[2];
	strcpy(user_input, "a");
	printf("%s", "\n [1] By manufacturer\n [2] By price\n [3] By date\n");
	scanf("%s", user_input);
	Products result;
	result.size = 0;
	if (!strcmp(user_input, "1")){
		char manufacturer[30];
		printf("%s", "Manufacturer: ");
		scanf("%s", manufacturer);
		filterByManufacturer(&result, manufacturer);
	}
	if (!strcmp(user_input, "2")){
		int price;
		printf("%s", "Price: ");
		scanf("%d", &price);
		filterByPrice(&result, price);
	}
	if (!strcmp(user_input, "3")){
		int date;
		printf("%s", "Date: ");
		scanf("%d", &date);
		filterByDate(&result, date);
	}
	int i;
	for (i=0; i<result.size; i++){
		printf("%d | ", result.products[i].id);
		printf("%s | ", result.products[i].type);
		printf("%s | ", result.products[i].model);
		printf("%s | ", result.products[i].manufacturer);
		printf("%d | ", result.products[i].price);
		printf("%d | ", result.products[i].date);
		printf("%d\n", result.products[i].quantity);
	}
}

void sortMenu() {
	char user_input[2];
	printf("%s", "\n [1] By price\n [2] By date\n");
	scanf("%s", user_input);
	Products result;
	result.size = 0;
	if (!strcmp(user_input, "1")){
		while (1) {
			printf("%s", "\n [1] Ascending\n [2] Descending\n");
			scanf("%s", user_input);
			if (!strcmp(user_input, "1")){
				sortByPrice(&result, ASC);
				strcpy(user_input, "a");
				break;
			}
			if (!strcmp(user_input, "2")){
				sortByPrice(&result, DSC);
				strcpy(user_input, "a");
				break;
			}
		}
	}
	if (!strcmp(user_input, "2")){
		while (1){
			printf("%s", "\n [1] Ascending\n [2] Descending\n");
			scanf("%s", user_input);
			if (!strcmp(user_input, "1")){
				sortByDate(&result, ASC);
				strcpy(user_input, "a");
				break;
			}
			if (!strcmp(user_input, "2")){
				sortByDate(&result, DSC);
				strcpy(user_input, "a");
				break;
			}
		}
	}
	int i;
	for (i=0; i<result.size; i++){
		printf("%d | ", result.products[i].id);
		printf("%s | ", result.products[i].type);
		printf("%s | ", result.products[i].model);
		printf("%s | ", result.products[i].manufacturer);
		printf("%d | ", result.products[i].price);
		printf("%d | ", result.products[i].date);
		printf("%d\n", result.products[i].quantity);
	}
}

void viewAll() {
	Products result;
	getAllProducts(&result);
	int i;
	for (i=0;i<result.size;i++) {
		printf("%d | ", result.products[i].id);
		printf("%s | ", result.products[i].type);
		printf("%s | ", result.products[i].model);
		printf("%s | ", result.products[i].manufacturer);
		printf("%d | ", result.products[i].price);
		printf("%d | ", result.products[i].date);
		printf("%d\n", result.products[i].quantity);
	}
}


int mainMenu(){
	char* command = (char*) malloc (2*sizeof(char));
	printf ("%s",menu);
	scanf("%s",command);
	if (strcmp(command,"x") == 0) {
		free (command);
		return 0;
	}
	if (strcmp(command,"1") == 0) {
		addMenu();
	}
	if (strcmp(command,"2") == 0){
		updateProductPriceMenu();
	}
	if (strcmp(command,"3") == 0) {
		updateProductQuantityMenu();
	}
	if (strcmp(command,"4") == 0) {
		removeProductMenu();
	}
	if (strcmp(command,"5") == 0) {
		filterMenu();
	}
	if (strcmp(command,"6") == 0) {
		sortMenu();
	}
	if (strcmp(command,"7") == 0) {
		viewAll();
	}
	free (command);
	return 1;
}


int run() {
	while (mainMenu() != 0) {
	}
	return 0;
}

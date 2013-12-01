/*
 * Console.c
 *
 *  Created on: 05.03.2013
 *      Author: mihai_000
 */

#include <stdio.h>
#include <string.h>
#include "domain.h"

int mainMenu(){
	char* command = (char*) malloc (2*sizeof(char));
	printf (" [1] Add new product\n [2] Update product\n [3] Remove product\n [4] Filter products\n [5] Sort products\n\n [x] Exit \n");
	scanf ("%s",command);
	if (strcmp(command,"x") == 0){
		return 0;
	}
	if (strcmp(command,"1") == 0){
		menuAddProduct();
	}
	return 1;
}

int menuAddProduct(){
	int id,price,quantity;
	char* type,model,manufacturer,date;

	printf("ID: ");
	scanf("%d",id);
	printf("Type: ");

	printf("Model: ");

	printf("Manufacturer: ");

	printf("Price: ");

	printf("Date: ");

	printf("Quantity: ");

}

int run(){
	while (mainMenu() != 0);
	return 0;
}


/*
 * test_repository.c
 *
 *  Created on: Mar 19, 2013
 *      Author: mihai
 */


#include <string.h>
#include <assert.h>
#include <stdio.h>

#include "test_repository.h"
#include "repository.h"


void test_addProductInRepo(){
	addProductInRepo("a", "a", "a", 10, 10, 10);
	Products result;
	getAllProductsFromRepo(&result);
	assert(result.products[0].id == 1);
	assert(!strcmp(result.products[0].type, "a"));
	assert(!strcmp(result.products[0].model, "a"));
	assert(!strcmp(result.products[0].manufacturer, "a"));
	assert(result.products[0].price == 10);
	assert(result.products[0].date == 10);
	assert(result.products[0].quantity == 10);
	addProductInRepo("b", "b", "b", 20, 20, 20);
	getAllProductsFromRepo(&result);
	assert(result.products[1].id == 2);
	assert(!strcmp(result.products[1].type, "b"));
	assert(!strcmp(result.products[1].model, "b"));
	assert(!strcmp(result.products[1].manufacturer, "b"));
	assert(result.products[1].price == 20);
	assert(result.products[1].date == 20);
	assert(result.products[1].quantity == 20);
}

void test_updatePriceInRepo(){
	assert(updatePriceInRepo(1, 100) == 0);
	assert(updatePriceInRepo(100, 1) == 1);
	Products result;
	getAllProductsFromRepo(&result);
	assert(result.products[0].price == 100);
}

void test_updateQuantityInRepo(){
	assert(updateQuantityInRepo(1, 100) == 0);
	assert(updateQuantityInRepo(100, 1) == 1);
	Products result;
	getAllProductsFromRepo(&result);
	assert(result.products[0].quantity == 100);
}

void test_deleteProductInRepo(){
	assert(deleteProductInRepo(1) == 0);
	assert(deleteProductInRepo(100) == 1);
	Products result;
	getAllProductsFromRepo(&result);
	assert(result.products[0].id != 1);
}

void test_repo_filterByManufacturer(){
	Products result;
	repo_filterByManufacturer(&result, "a");
	int i;
	for (i=0; i<result.size; i++){
		assert(!strcmp(result.products[i].manufacturer, "a"));
	}
}

void test_repo_filterByPrice(){
	Products result;
	repo_filterByPrice(&result, 20);
	int i;
	for (i=0; i<result.size; i++){
		assert(result.products[i].price == 20);
	}
}

void test_repo_filterByDate(){
	Products result;
	repo_filterByDate(&result, 10);
	int i;
	for (i=0; i<result.size; i++){
		assert(result.products[i].date == 10);
	}
}

void test_repo(){
	FILE *file;
	file = fopen("testRepo.txt", "w");
	fclose(file);
	initRepo("testRepo.txt");
	test_addProductInRepo();
	test_updatePriceInRepo();
	test_updateQuantityInRepo();
	test_deleteProductInRepo();
	test_repo_filterByManufacturer();
	test_repo_filterByPrice();
}

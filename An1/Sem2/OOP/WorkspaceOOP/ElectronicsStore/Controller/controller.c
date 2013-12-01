/*
 * controller.c
 *
 *  Created on: Mar 11, 2013
 *      Author: mihai
 */
#include "../Domain/product.h"
#include "../Repository/repository.h"
#include <string.h>

int addProduct(char* type, char* model, char* manufacturer, int price, int date, int quantity) {
	addProductInRepo(type, model, manufacturer, price, date, quantity);
	return 0;
}

void getAllProducts(Products* result){
	getAllProductsFromRepo(result);
}

int updatePrice(int id, int price){
	return updatePriceInRepo(id, price);
}

int updateQuantity(int id, int quantity){
	return updateQuantityInRepo(id, quantity);
}

int deleteProduct(int id){
	return deleteProductInRepo(id);
}

void filterByManufacturer(Products* result, char* manufacturer){
	repo_filterByManufacturer(result, manufacturer);
}

void filterByPrice(Products *result ,int price){
	repo_filterByPrice(result, price);
}

void filterByDate(Products *result ,int date){
	repo_filterByDate(result, date);
}

void sortByPrice(Products *result, int direction){
	repo_sortByPrice(result, direction);
}

void sortByDate(Products *result, int direction){
	repo_sortByDate(result, direction);
}

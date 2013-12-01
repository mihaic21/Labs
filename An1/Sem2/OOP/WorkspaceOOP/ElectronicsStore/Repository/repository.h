/*
 * repository.h
 *
 *  Created on: Mar 18, 2013
 *      Author: mihai
 */

#ifndef REPOSITORY_H_
#define REPOSITORY_H_

#include "../Domain/product.h"

/*
 * ASC and DSC constants represent the sorting direction passed to the sort functions
 */
#define ASC 1
#define DSC -1

/*
 * The struct that holds the array of products and the size of the list
 */
typedef struct{
	Product products[100];
	int size;
}Products;

/*
 * Method to initialize the repository
 * Reads the products from the file and loads them into memory
 */
void initRepo(char* filename);

/*
 * Method that adds a new product to the repository
 */
int addProductInRepo(
	char* type,
	char* model,
	char* manufacturer,
	int price,
	int date,
	int quantity);

/*
 * Method that copies the repository to the result address
 */
void getAllProductsFromRepo(Products *result);

/*
 * Method that updates the price of a product in the repository
 * Returns 1 if id was not found
 * Returns 0 if the price was updated successfully
 */
int updatePriceInRepo(int id, int price);

/*
 * Method that updates the quantity of a product in the repository
 * Returns 1 if id was not found
 * Returns 0 if the quantity was updated successfully
 */
int updateQuantityInRepo(int id, int quantity);

/*
 * Method that deletes a product from the repository
 * Returns 1 if id was not found
 * Returns 0 if the product was deleted successfully
 */
int deleteProductInRepo(int id);

/*
 * Method that filters all products by manufacturer
 * The resulting list will be placed at the result address
 */
void repo_filterByManufacturer(Products* result, char* manufacturer);

/*
 * Method that filters all products by price
 * The resulting list will be placed at the result address
 */
void repo_filterByPrice(Products *result ,int price);

/*
 * Method that filters all products by date
 * The resulting list will be placed at the result address
 */
void repo_filterByDate(Products *result ,int date);

/*
 * Method that sorts all products by price
 * The resulting list will be placed at the result address
 */
void repo_sortByPrice(Products *result, int direction);

/*
 * Method that sorts all products by date
 * The resulting list will be placed at the result address
 */
void repo_sortByDate(Products *result, int direction);

#endif /* REPOSITORY_H_ */

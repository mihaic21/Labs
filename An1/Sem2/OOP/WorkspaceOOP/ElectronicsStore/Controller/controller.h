/*
 * controller.h
 *
 *  Created on: Mar 18, 2013
 *      Author: mihai
 */

#ifndef CONTROLLER_H_
#define CONTROLLER_H_

#include "../Repository/repository.h"

/*
 * Controller method that adds a product to the repository
 * Returns 0
 */
int addProduct(char* type, char* model, char* manufacturer, int price, int date, int quantity);

/*
 * Controller method to get all the products from the repository
 * The products will be placed at the result address
 */
void getAllProducts(Products *result);

/*
 * Controller method to update the price of a product in the repository
 */
int updatePrice(int id, int price);

/*
 * Controller method to update the quantity of a product in the repository
 */
int updateQuantity(int id, int quantity);

/*
 * Controller method to filter all products by manufacturer
 * The resulting list will be put at the result address
 */
void filterByManufacturer(Products *result ,char* manufacturer);

/*
 * Controller method to filter all products by price
 * The resulting list will be put at the result address
 */
void filterByPrice(Products *result ,int price);

/*
 * Controller method to filter all products by date
 * The resulting list will be put at the result address
 */
void filterByDate(Products *result ,int date);

/*
 * Controller method to sort the list of products by price
 * The resulting list will be put at the result address
 */
void sortByPrice(Products *result, int direction);

/*
 * Controller method to sort the list of products by date
 * The resulting list will be put at the result address
 */
void sortByDate(Products *result, int direction);

/*
 * Controller method to delete a product from the repository
 * Returns the result of the deleteProductInRepo function
 */
int deleteProduct(int id);

#endif /* CONTROLLER_H_ */

/*
 * product.h
 *
 *  Created on: Mar 11, 2013
 *      Author: mihai
 */

#ifndef PRODUCT_H_
#define PRODUCT_H_

/*
 * The structure of a product, containing id, type, model, manufacturer, price, date and quantity
 */
typedef struct {
	int id;
	char type[30];
	char model[30];
	char manufacturer[30];
	int price;
	int date;
	int quantity;
}Product;



#endif /* PRODUCT_H_ */

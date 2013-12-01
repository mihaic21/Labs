/*
 * domain.h
 *
 *  Created on: 05.03.2013
 *      Author: mihai_000
 */

#ifndef DOMAIN_H_
#define DOMAIN_H_

typedef struct{
	int id;
	char* type;
	char* model;
	char* manufacturer;
	int price;
	char* date;
	int quantity;
}product;

#endif /* DOMAIN_H_ */

/*
 * app.c
 *
 *  Created on: Mar 11, 2013
 *      Author: mihai
 */

#include "UI/console.h"
#include <string.h>
#include "Repository/repository.h"
#include "Repository/test_repository.h"

int main(){
	test_repo();
	char fileName[30];
	strcpy(fileName,"products.txt");
	initRepo(fileName);
	run();
	return 0;
}

/*
 * utils.c
 *
 *  Created on: 16 Mar 2013
 *      Author: pcnc
 */
#include <string.h>
#include <stdlib.h>

char* copyString(char* s) {
	/*
	 * Copies string
	 */
	if (s != 0) {
		char* temp = (char*) malloc((strlen(s) + 1) * sizeof(char));
		strcpy(temp, s);
		return temp;
	} else
		return 0;
}

/*
 ============================================================================
 Name        : BlockManagement.c
 Author      : pcnc
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "domain/apExpense.h"
#include "repository/repository.h"
#include "ui/ui.h"
#include "domain/tests.h"

int main(void) {
	/*
	 * main function
	 */
	setbuf(stdout, 0);

	tests();

	readFromFile();

	runUI();

	destroyRepo();
	return 0;
}

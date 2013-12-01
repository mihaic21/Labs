//============================================================================
// Name        : Block.cpp
// Author      : Mihai Costiug
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include "ui/UI.h"

#include "domain/TestExpense.h"
#include "repository/testRepository.h"
#include "controller/testController.h"
#include "domain/testValidator.h"
#include "repository/FileRepository.h"


void test(){
	testExpense();
	testMRepository();
	testController();
	testValidator();
}


int main(){
	test();
	FileRepository* repo=new FileRepository("expenses.txt");
	Controller* cont=new Controller(repo);
	UI* ui=new UI(cont);
	ui->run();
	return 0;
}

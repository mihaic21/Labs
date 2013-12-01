#include "myexpense.h"

#include <QtGui>
#include <QApplication>

#include "src/Domain/testExpense.h"
#include "src/Repository/testRepository.h"
#include "src/Repository/expenseRepository.h"
#include "src/Controller/expenseManager.h"

int main(int argc, char *argv[])
{
	testExpense();
	testRepo();

    QApplication a(argc, argv);

	//create repository
	ExpenseRepository* repo = new ExpenseRepository("expense.txt");
	//create controller
	ExpenseManager ctrl(repo);

	MyExpense* expenseApp = new MyExpense(&ctrl);
	expenseApp->show();

    return a.exec();
}

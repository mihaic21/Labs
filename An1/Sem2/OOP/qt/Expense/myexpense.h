#ifndef MYEXPENSE_H
#define MYEXPENSE_H

#include <QtGui>
#include "ui_myexpense.h"
#include "src/Controller/expenseManager.h"

class MyExpense : public QWidget
{
    Q_OBJECT

private:
    ExpenseManager* Ctrl;

	QListWidget* ExpensesList;

    QLabel* ExpenseLbl;
	QLabel* TypeLbl;
	QLabel* QuantityLbl;

	QLineEdit* TypeEdit;
	QLineEdit* QuantityEdit;

	QPushButton* SaveButton;

public:
    MyExpense(ExpenseManager* ctrl, QWidget *parent = 0);
    ~MyExpense();

private slots:
	void listExpensesChanged();
	void addNewExpense();

private:
    Ui::MyExpenseClass ui;

    void initTest();
	void populateExpensesList();
	void Exception();
};

#endif // MYEXPENSE_H

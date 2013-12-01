#include "myexpense.h"

MyExpense::MyExpense(ExpenseManager* ctrl, QWidget *parent)
{
	ui.setupUi(this);
	this->Ctrl = ctrl;
	this->initTest();
	this->populateExpensesList();
}

MyExpense::~MyExpense(){}

void MyExpense::initTest()
{
	// create main layout
	QHBoxLayout* mainLayout = new QHBoxLayout;

	// create layout for the left side
	QVBoxLayout* leftLayout = new QVBoxLayout;

	// create layout for the upper side of the left side: text and expenses list
	QHBoxLayout* expenseLayout = new QHBoxLayout;
	this->ExpenseLbl = new QLabel("My Expenses");

	expenseLayout->addWidget(this->ExpenseLbl);
	QWidget* expenseWidget = new QWidget;
	expenseWidget->setLayout(expenseLayout);

	// add the upper side - the expenses list
	leftLayout->addWidget(expenseWidget);
	this->ExpensesList = new QListWidget;

	// set the selection model
	this->ExpensesList->setSelectionMode(QAbstractItemView::SingleSelection);

	// add connection
	QObject::connect(this->ExpensesList, SIGNAL(itemSelectionChanged()), this,
						SLOT(listExpensesChanged()));
	leftLayout->addWidget(this->ExpensesList);


	QWidget* leftSide = new QWidget;
	leftSide->setLayout(leftLayout);

	// create labels and edits for: type and quantity
	// type
	this->TypeLbl = new QLabel("&Type");
	this->TypeEdit = new QLineEdit;
	this->TypeLbl->setBuddy(this->TypeEdit);
	// quantity
	this->QuantityLbl = new QLabel("&Quantity");
	this->QuantityEdit = new QLineEdit;
	this->QuantityLbl->setBuddy(this->QuantityEdit);

	// create a GridLayout
	QGridLayout* gridLayout = new QGridLayout;
	gridLayout->addWidget(this->TypeLbl, 0, 0);
	gridLayout->addWidget(this->TypeEdit, 0, 1);
	gridLayout->addWidget(this->QuantityLbl, 1, 0);
	gridLayout->addWidget(this->QuantityEdit, 1, 1);
	QWidget* infoWidget = new QWidget;
	infoWidget->setLayout(gridLayout);

	// create the buttons
	QHBoxLayout* layoutButtons = new QHBoxLayout;
	QHBoxLayout* layoutBakeButton = new QHBoxLayout;
	this->SaveButton = new QPushButton("&Save");
	// add connection
	QObject::connect( this->SaveButton, SIGNAL(clicked()), this,
					SLOT(addNewExpense()) );

	layoutButtons->addWidget(this->SaveButton);
	QWidget* buttonsWidget = new QWidget;
	buttonsWidget->setLayout(layoutButtons);

	// now create the right layout and widget
	QVBoxLayout* rightLayout = new QVBoxLayout;
	rightLayout->addWidget(infoWidget);
	rightLayout->addWidget(buttonsWidget);

	QWidget* rightSide = new QWidget;
	rightSide->setLayout(rightLayout);

	// add the left side
	mainLayout->addWidget(leftSide);
	// add the right side
	mainLayout->addWidget(rightSide);

	this->setLayout(mainLayout);
}

void MyExpense::populateExpensesList(){
	if (this->ExpensesList->count() > 0 )
		this->ExpensesList->clear();

	LinkedList<Expense*>* allExpenses = this->Ctrl->sortByType();
	int size = allExpenses->getSize();
	for(int i = 0; i < size; i++)
	{
		Expense* e = (Expense*)allExpenses->getElementByPosition(i);
		QString aux = QString::fromStdString(e->getType());
		this->ExpensesList->addItem(aux);
	}

	// select the first item in the list
	if (size > 0)
		this->ExpensesList->setCurrentRow(0);
}

void MyExpense::listExpensesChanged()
{
	if (this->ExpensesList->count() == 0)
		return;

	QModelIndexList index = this->ExpensesList->selectionModel()
							->selectedIndexes();

	if (index.size() == 0)
	{
		this->TypeEdit->clear();
		this->QuantityEdit->clear();
		return;
	}

	int idx = index.at(0).row();
	LinkedList<Expense*>* allExpenses = this->Ctrl->sortByType();
	Expense* e = allExpenses->getElementByPosition(idx);
	QString name = QString::fromStdString(e->getType());
	this->TypeEdit->setText(name);
	QString quantity = QString::number(e->getAmount());
	this->QuantityEdit->setText(quantity);
}

void MyExpense::addNewExpense()
{
	QString qQuantity = this->QuantityEdit->text();
	int quantity = qQuantity.toInt();
	QString qName = this->TypeEdit->text();
	string name = qName.toStdString();

	try{
		this->Ctrl->saveExpense(name, quantity);
	}catch(...){
		this->Exception();
	}

	// repopulate the list
	this->populateExpensesList();
}

void MyExpense::Exception(){
	QErrorMessage* amountException = new QErrorMessage();
	amountException->showMessage("The amount should be a positive integer!");
}

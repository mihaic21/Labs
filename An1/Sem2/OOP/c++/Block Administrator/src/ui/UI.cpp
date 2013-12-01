#include "UI.h"
#include<iostream>

using namespace std;

UI::UI(Controller* c){
	this->cont=c;
}

void UI::mainMenu(){
	cout<<"Select an option:\n";
	cout<<"[1] Add new expense\n";
	cout<<"[2] Available expenses\n";
	cout<<"[3] Delete expense\n";
	cout<<"[4] Update expense\n";
	cout<<"[5] Filter\n";
	cout<<"[6] Sort\n";
	cout<<"[0] Exit\n";
}

void UI::add(){
	Expense p;
	cin>>p;
	try {
	this->cont->add(p);
	} catch(RepositoryException &e) {
		cout<<e.getMsg()<<"\n";
	}
}

void UI::printHeader(){
	cout<<setw(4)<<"ID"<<"|"<<setw(10)<<"Day"<<"|"<<setw(10)<<"Amount"<<"|"<<setw(15)<<"Type"<<"|"<<"\n";
}

void UI::getAll(){
	this->printHeader();
	vector<Expense*> all=this->cont->getAll();
	int size=this->cont->size();
	for(int i=0; i<size; i++)
		cout<<*all[i];
}

void UI::run(){
	int option, cont=1;
	string read;
	while(cont==1){
		try
		{
			this->mainMenu();
			cout<<"\n>";
			cin>>read;
			option=convert(read);
			switch(option){
			case(1):
				this->add();
				break;
			case(2):
				this->getAll();
				break;
			case(3):
				this->remove();
				break;
			case(4):
				this->update();
				break;
			case(5):
				this->filter();
				break;
			case(6):
				this->sort();
				break;
			case(0):
				cont=0;
				break;
			default:
				cout<<"Invalid Selection!";
				break;
			}
		}
			catch(RepositoryException& e)
			{
				cout <<e.getMsg()<<"\n";
			}
			catch(ExpenseException& pe)
			{
				cout<<pe.getMsg();
			}
			catch(IException& i)
			{
				cout<<i.getMsg();
			}
			cout<<"\n\n";
	}
}

UI::~UI(){
	delete this->cont;
}

void UI::remove(){
	int id;
	cout<<"Expense id:";
	cin>>id;
	this->cont->remove(id);
}

void UI::update(){
	Expense p;
	cout<<"Enter the expense ID and new properties:\n";
	cout<<"  ";
	cin>>p;
	this->cont->update(p.getId(), p);
}

void UI::sortMenu(){
	cout<<setw(4)<<' '<<"[1] Sort by amount (ascending)\n";
	cout<<setw(4)<<' '<<"[2] Sort by amount(descending)\n";
	cout<<setw(4)<<' '<<"[3] Sort by type (ascending)\n";
	cout<<setw(4)<<' '<<"[4] Sort by type (descending)\n";
	cout<<setw(4)<<' '<<"[0] Return\n";
}

void UI::sort(){
	this->sortMenu();
	int sec;
	string read;
	cout<<setw(4)<<' '<<"Sort by: ";
	cin>>read;
	sec=convert(read);
	switch(sec){
		case(1):
			this->sortByAmountA();
			break;
		case(2):
			this->sortByAmountD();
			break;
		case(3):
			this->sortByTypeA();
			break;
		case(4):
			this->sortByTypeD();
			break;
		case(0):
			break;
		default:
			cout<<"Invalid choice\n";
			break;
	}

}

void UI::sortByAmountA(){
	vector<Expense*> sort;
	sort=this->cont->sortByAmountA();
	int size=this->cont->size();
	this->printHeader();
	for(int i=0; i<size; i++)
		cout<<*sort[i];
}

void UI::sortByAmountD(){
	vector<Expense*> sort;
	sort=this->cont->sortByAmountD();
	int size=this->cont->size();
	this->printHeader();
	for(int i=0; i<size; i++)
		cout<<*sort[i];
}

void UI::sortByTypeA(){
	vector<Expense*> sort;
	sort=this->cont->sortByTypeA();
	int size=this->cont->size();
	this->printHeader();
	for(int i=0; i<size; i++)
		cout<<*sort[i];
}

void UI::sortByTypeD(){
	vector<Expense*> sort;
	sort=this->cont->sortByTypeD();
	int size=this->cont->size();
	this->printHeader();
	for(int i=0; i<size; i++)
		cout<<*sort[i];
}

void UI::filterMenu(){
	cout<<setw(4)<<' '<<"[1] Filter by amount\n";
	cout<<setw(4)<<' '<<"[2] Filter by day\n";
	cout<<setw(4)<<' '<<"[0] Return\n";
}

void UI::filter(){
	this->filterMenu();
	int sec1;
	string read;
	cout<<setw(4)<<' '<<"Filter by: ";
	cin>>read;
	sec1=convert(read);
	switch(sec1){
		case(1):
			this->filterByAmount();
			break;
		case(2):
			this->filterByDay();
			break;
		case(0):
			break;
		default:
			cout<<"Invalid choice\n";
			break;
	}
}

void UI::filterByAmount(){
	int amount;
	cout<<setw(4)<<' '<<"Amount: ";
	cin>>amount;
	vector<Expense*> found=this->cont->filterByAmount(amount);
	int size=found.size();
	this->printHeader();
	for(int i=0; i<size; i++)
		cout<<*found[i];
}

void UI::filterByDay(){
	int day;
	cout<<setw(4)<<' '<<"Day: ";
	cin>>day;
	vector<Expense*> found=this->cont->filterByDay(day);
	int size=found.size();
	this->printHeader();
	for(int i=0; i<size; i++)
		cout<<*found[i];
}

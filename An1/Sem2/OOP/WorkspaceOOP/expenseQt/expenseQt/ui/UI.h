#ifndef UI_H_
#define UI_H_

#include "../controller/Controller.h"

class UI{
private:
	Controller* cont;
public:
	UI(Controller* cont);

	void mainMenu();

	void add();

	void remove();

	void update();

	void getAll();

	void sort();

	void sortMenu();

	void printHeader();

	void sortByAmountA();

	void sortByAmountD();

	void sortByTypeA();

	void sortByTypeD();

	void filterByDay();

	void filterByAmount();

	void filterMenu();

	void filter();

	void run();

	~UI();
};
//------------------------------------------------------------------------------------------------------------



#endif /* UI_H_ */

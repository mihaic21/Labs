
#include "utils.h"
#include <sstream>
#include <string>
#include <algorithm>

vector<string> tokenize(const string& s, char delimiter){
	vector<string> result;
	string text(s);
	string token = "";

	istringstream iss(text);
	while (getline(iss, token, delimiter)){
		token.erase(remove(token.begin(), token.end(), ' '), token.end());
		result.push_back(token);
	}
	return result;
}

bool compareByAmountA(Expense* p1, Expense* p2){
	return p1->getAmount()<p2->getAmount();
}

bool compareByAmountD(Expense* p1, Expense* p2){
	return p1->getAmount()>p2->getAmount();
}

bool compareByTypeA(Expense* p1, Expense* p2){
	string a = p1->getType();
	string b = p2->getType();
	return (a.compare(b)<0)?true:false;
}

bool compareByTypeD(Expense* p1, Expense* p2){
	string a = p1->getType();
	string b = p2->getType();
	return (a.compare(b)>0)?true:false;
}

int convert(string str) throw (IException){
	const char* nstr = str.c_str();
	const char* pars = nstr;
	string numbers = "1234567890";
	while (*pars != '\0')
		if(strchr(numbers.c_str(), *pars)!=NULL)
			pars++;
		else
			break;
	if(*pars != '\0')
		throw IException("WRONG INPUT TYPE!\n");
	int conv = atoi(nstr);
	return conv;
}

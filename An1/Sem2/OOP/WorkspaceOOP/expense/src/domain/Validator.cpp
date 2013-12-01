#include "Validator.h"
#include <string>
#include "../utils/utils.h"

void Validator::validate(const Expense& e)throw(RepositoryException){
	string str = "";
	if(e.getId()<=0)
		str+="Invalid ID!\n";
	if(e.getAmount()<=0)
		str+="Invalid Amount!\n";
	if(e.getDay()<=0 || e.getDay() > 31)
		str+="Invalid Day!\n";
	const char* type=e.getType().c_str();
	if (strlen(type)==0 || (e.getType()!="housekeeping"&& e.getType()!="food" && e.getType()!="transport"
			&& e.getType()!="clothing" && e.getType()!="telephone" && e.getType()!="others"))
		str+="Invalid type!\n";
	else if (strlen(e.getType().c_str())==0)
		str+="Invalid Type!\n";
	if(str != "")
		throw RepositoryException(str);
}

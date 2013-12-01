/*
 * utils.cpp
 *
 *  Created on: Apr 5, 2013
 *      Author: Amalia
 */

#include <iostream>
#include <string>
#include <cstring>
#include <cassert>
#include <stdexcept>
#include <sstream>
using namespace std;

string NumberToString (int Number )
  {
     ostringstream ss;
     ss << Number;
     return ss.str();
  }

string copyString(string s)
{
	string clone = s;
	return clone;
}

//-----------------------------------------------------------------------------------------------------------------

int validPositiveInteger(string str)
{
	try{
			int n = str.length();
			int i = 0;
			for(i = 0; i < n; i++)
			{
				if( !isdigit(str[i]) )
					throw 0;
			}
			throw 1;
	}
	catch (int result){ //i.e. the conversion could not be made
		return result;
	}
}


//----------------------------------------------------------------------------------------

string validateExpenseData(string apNumber, string amount, string type){

	string res = "";
	if ((apNumber == "") || (amount == "") || (type == "")){
		res = "Uninitialized fields!";
		return res;
	}

	if(!validPositiveInteger(apNumber)){
		string err = "Invalid apartament number! ";
		res = res + " " + err;
	}

	if(!validPositiveInteger(amount)){
			string err = "Invalid amount number! ";
			res = res + " " + err;
	}

	if ((type!="water")&&(type!="heating")&&(type!="gas")&&
			(type!="illuminating")&&(type!="others")){
		string err = "Wrong entry for type! ";
		res = res + " " + err;
	}

	return res;
}

//-----------------------------------------------------------------------------------------------------------------



void testUtils(){

	//Test copyString
	string s1, s2;
	s1 = "asfg";
	s2 = copyString(s1);
	assert(s2 == "asfg");
	s1 = "";
	s2 = copyString(s1);
	assert(s2 == "");


	//Test validPositiveInteger
	s1="123";
	assert(validPositiveInteger(s1)==1);
	s1="12a";
	assert(validPositiveInteger(s1)==0);

	cout<<"Test utils successful!"<<endl;

}

#ifndef UTIL_H_
#define UTIL_H
#include <cstring>
#include <string>
#include <sstream>
using namespace std;

string NumberToString ( int Number);

/*
 * Makes a copy of a string
 * Input: s - string
 * Output: s' - copy of s
 */

string copyString(string s);


//-----------------------------------------------------------------------------------------------------------------
/*
 * Checks if a given string is a valid positive integer. It is considered valid if it only contains digits.
 * Input: str - string
 * Output:  1 - if str is valid
 * 			0 - otherwise
 */
int validPositiveInteger(string str);

//-----------------------------------------------------------------------------------------------------------------

/*
 * Tests the Utils functions
 * Input: -
 * Output: -
 */
void testUtils();

#endif

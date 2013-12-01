#ifndef EXCEPTIONS_H
#define EXCEPTIONS_H

#include <string>
#include <iostream>
using namespace std;

class Exceptions
{
string msg;
public:

    Exceptions();
    Exceptions(string m): msg(m){}
    string getMessage(){return msg;}

};

#endif // EXCEPTIONS_H

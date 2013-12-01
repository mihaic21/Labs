#include<loan.h>

using namespace std;

Loan::Loan()
{
}

Loan::Loan(int id, string name)
{
    this->id=id;
    this->name=name;
}

int Loan::getId()
{
    return this->id;
}

void Loan::setId(int id)
{
    this->id=id;
}

string Loan::getName()
{
    return this->name;
}

void Loan::setName(string name)
{
    this->name=name;
}

#include "dvd.h"

using namespace std;

DVD::DVD()
{
}


DVD::DVD(int id, string name, bool available)
{
    this->id = id;
    this->name = name;
    this->available = available;
}

void DVD::setId(int id)
{
    this->id=id;
}

void DVD::setName(string name)
{
    this->name=name;
}

string DVD::getName()
{
    return this->name;
}

int DVD::getId()
{
    return this->id;
}

void DVD::setAvailable(bool available){
    this->available = available;
}

bool DVD::isAvailable(){
    return this->available;
}

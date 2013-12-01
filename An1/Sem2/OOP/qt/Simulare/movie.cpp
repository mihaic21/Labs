#include "movie.h"

using namespace std;

Movie::Movie()
{
}

Movie::Movie(string name, string actor, bool available){
    this->name = name;
    this->actor = actor;
    this->available = available;
}

void Movie::setActor(string actor){
    this->actor = actor;
}

void Movie::setName(string name){
    this->name = name;
}

void Movie::setAvailable(bool available){
    this->available = available;
}

bool Movie::isAvailable(){
    return this->available;
}

string Movie::getActor(){
    return this->actor;
}

string Movie::getName(){
    return this->name;
}

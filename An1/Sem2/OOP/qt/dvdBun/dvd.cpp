#include "dvd.h"

Dvd::Dvd(){
}

Dvd::Dvd(int id, QString title){
    this->id = id;
    this->title = title;
}

int Dvd::getId(){
    return this->id;
}

QString Dvd::getTitle(){
    return this->title;
}

#include "task.h"

Task::Task()
{
}

Task::Task(int id, QString description, QString date, int hours){
    this->id = id;
    this->description = description;
    this->date = date;
    this->hours = hours;
}

void Task::setId(int id){
    this->id = id;
}

void Task::setDescription(QString description){
    this->description = description;
}

void Task::setDate(QString date){
    this->date = date;
}

void Task::setHours(int hours){
    this->hours = hours;
}


int Task::getId(){
    return this->id;
}

QString Task::getDescription(){
    return this->description;
}

QString Task::getDate(){
    return this->date;
}

int Task::getHours(){
    return this->hours;
}

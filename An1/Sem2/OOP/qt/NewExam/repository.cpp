#include "repository.h"

#include <QFile>
#include <QTextStream>
#include <QStringList>
#include "task.h"

Repository::Repository()
{
}

Repository::Repository(QString fileName){
    this->fileName = fileName;
    this->readFromFile();
}


void Repository::readFromFile(){
    QFile file(this->fileName);
    file.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&file);
    while (!in.atEnd()){
        int id;
        QString description;
        QString date;
        int hours;
        QString line = in.readLine();
        QStringList sl = line.split(",");
        id = sl[0].toInt();
        description = sl[1];
        date = sl[2];
        hours = sl[3].toInt();
        Task *t = new Task (id,description,date,hours);
        this->tasks.append(t);
    }
    file.close();
}

void Repository::writeToFile(){
    QFile file(this->fileName);
    file.open(QIODevice::WriteOnly|QIODevice::Text);
    QTextStream out(&file);

    for (int i=0; i<this->tasks.length(); i++){
        out << this->tasks[i]->getId()<<','<<this->tasks[i]->getDescription()<<','<<this->tasks[i]->getDate()<<','<<this->tasks[i]->getHours()<<endl;
    }
    file.close();
}

QList<Task*> Repository::getAll(){
    return this->tasks;
}

void Repository::addTask(int id, QString description, QString date, int hours){
    Task *task = new Task (id, description, date, hours);
    this->tasks.append(task);
    this->writeToFile();
}

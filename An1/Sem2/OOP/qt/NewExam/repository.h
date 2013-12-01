#ifndef REPOSITORY_H
#define REPOSITORY_H

#include <QString>
#include <QList>

#include "task.h"


class Repository
{
private:
    QString fileName;
    QList<Task*> tasks;
    void readFromFile();
    void writeToFile();

public:
    Repository();
    Repository (QString fileName);
    QList<Task*> getAll();
    void addTask(int id, QString description, QString date, int hours);
};

#endif // REPOSITORY_H

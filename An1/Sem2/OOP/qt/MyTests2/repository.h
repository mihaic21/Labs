#ifndef REPOSITORY_H
#define REPOSITORY_H

#include <QString>
#include <QList>
#include "question.h"

class Repository
{
private:
    QString filename;
    void readFromFile();
    QList<Question*> questions;
public:
    Repository();
    Repository(QString filename);
    QList<Question*> getAll();
};

#endif // REPOSITORY_H

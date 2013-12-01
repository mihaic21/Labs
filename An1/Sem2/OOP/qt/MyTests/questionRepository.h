#ifndef QUESTIONREPOSITORY_H
#define QUESTIONREPOSITORY_H

#include "question.h"
#include <QList>

class QuestionRepository
{
private:
    QString filename;
    QList<Question*> questions;
    void readFromFile();
public:
    QuestionRepository (QString filename);
    QList<Question*> getAll();
};

#endif // QUESTIONREPOSITORY_H

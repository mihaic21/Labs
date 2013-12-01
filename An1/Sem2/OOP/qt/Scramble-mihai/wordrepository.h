#ifndef WORDREPOSITORY_H
#define WORDREPOSITORY_H

#include <QString>
#include <QList>

#include "word.h"

class WordRepository
{
private:
    QString filename;
    QList<Word> elements;
    void readFromFile();
public:
    WordRepository(QString filename);
    QList<Word> getAll();
};

#endif // WORDREPOSITORY_H

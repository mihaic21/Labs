#include <QFile>
#include <QTextStream>
#include "wordrepository.h"

WordRepository::WordRepository(QString filename)
{
    this->filename = filename;
    this->readFromFile();
}

QList<Word> WordRepository::getAll(){
    return this->elements;
}

void WordRepository::readFromFile()
{
    QFile repoFile(this->filename);
    repoFile.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&repoFile);
    while (!in.atEnd()){
        QString word;
        int score;
        in >> word >> score;
        Word w(word, score);
        this->elements.append(w);
    }

    repoFile.close();
}

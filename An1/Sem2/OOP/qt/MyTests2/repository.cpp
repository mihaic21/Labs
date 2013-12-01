#include "repository.h"
#include <QFile>
#include <QTextStream>
#include <QStringList>


Repository::Repository()
{
}

Repository::Repository(QString filename){
    this->filename = filename;
    this->readFromFile();
}

void Repository::readFromFile(){
    QFile file(this->filename);
    file.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&file);
    while (!in.atEnd()){
        int id;
        QString text;
        QChar correctAnswer;
        QString line = in.readLine();
        QStringList sl = line.split("|");
        id = sl[0].toInt();
        text = sl[1];
        correctAnswer = sl[2][0];
        Question *q = new Question(id, text, correctAnswer);
        this->questions.append(q);
    }
    file.close();
}

QList<Question *> Repository::getAll(){
    return this->questions;
}

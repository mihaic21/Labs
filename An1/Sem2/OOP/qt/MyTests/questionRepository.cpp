#include <QFile>
#include <QTextStream>
#include <QString>
#include <QChar>
#include <QStringList>

#include "questionRepository.h"

QuestionRepository::QuestionRepository(QString filename){
    this->filename=filename;
    this->readFromFile();
}

QList<Question*> QuestionRepository::getAll(){
    return this->questions;
}

void QuestionRepository::readFromFile(){
    QFile repoFile (this->filename);
    repoFile.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&repoFile);
    while (!in.atEnd()){
        int id;
        QString text;
        QChar correctAnswer;
        QString line = in.readLine();
        QStringList sl = line.split("|");
        id = sl[0].toInt();
        text = sl[1];
        correctAnswer = sl[2][0];
        Question* q = new Question(id, text, correctAnswer);
        this->questions.append(q);
    }
    repoFile.close();
}

#ifndef QUESTION_H
#define QUESTION_H

#include <QChar>
#include <QString>

class Question
{
public:
    Question();
    Question(int id, QString text, QChar correctAnswer);
    int id;
    QString text;
    QChar correctAnswer;
};

#endif // QUESTION_H

#ifndef QUESTION_H
#define QUESTION_H

#include<QString>
#include<QChar>
class Question
{
private:
    int id;
    QString text;
    QChar correctAnswer;
public:
    Question(){};
    Question(int id, QString text, QChar correctAnswer);
    QString getText();
    QChar getCorrectAnswer();
};

#endif // QUESTION_H

#include "question.h"
#include<QString>
#include<QChar>

Question::Question(int id, QString text, QChar correctAnswer){
    this->id=id;
    this->text=text;
    this->correctAnswer=correctAnswer;
}

QString Question::getText(){
    return this->text;
}

QChar Question::getCorrectAnswer(){
    return this->correctAnswer;
}

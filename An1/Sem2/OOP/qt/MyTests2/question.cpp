#include "question.h"

Question::Question()
{
}

Question::Question(int id, QString text, QChar correctAnswer){
    this->id = id;
    this->text = text;
    this->correctAnswer = correctAnswer;
}

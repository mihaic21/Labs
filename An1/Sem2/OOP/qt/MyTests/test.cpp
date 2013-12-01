#include "test.h"

Test::Test(int id){
    this->id=id;
    this->correctAnswers=0;
    this->questionCount=0;
    this->maxQuestionCount=3;
}

int Test::getQuestionCount(){
    return this->questionCount;
}

void Test::incrementQuestionCount(){
    this->questionCount++;
}

int Test::getCorrectAnswers(){
    return this->correctAnswers;
}

void Test::incrementCorrectAnswers(){
    this->correctAnswers;
}

int Test::getId(){
    return this->id;
}

int Test::getMaxQuestionCount(){
    return this->maxQuestionCount;
}

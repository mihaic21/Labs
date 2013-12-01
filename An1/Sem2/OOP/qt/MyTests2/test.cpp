#include "test.h"

Test::Test()
{
}

Test::Test(int id){
    this->id = id;
    this->correctAnswers = 0;
    this->questionCount = 0;
    this->maxQuestionCount = 3;
}

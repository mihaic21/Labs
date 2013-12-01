#include "controller.h"

Controller::Controller(QuestionRepository *questionRepo, TestRepository *testRepo){
    this->questionRepo = questionRepo;
    this->testRepo = testRepo;
}

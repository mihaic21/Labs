#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "questionRepository.h"
#include "testRepository.h"

class Controller
{
private:
    QuestionRepository* questionRepo;
    TestRepository* testRepo;
public:
    Controller (QuestionRepository* questionRepo, TestRepository* testRepo);
};

#endif // CONTROLLER_H

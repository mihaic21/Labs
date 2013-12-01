#include "controller.h"
#include "test.h"

Controller::Controller()
{
}

Controller::Controller(Repository *repo){
    this->repo = repo;
}

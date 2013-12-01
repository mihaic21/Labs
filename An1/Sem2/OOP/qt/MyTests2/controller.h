#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "repository.h"

class Controller
{
private:
    Repository *repo;
public:
    Controller();
    Controller(Repository *repo);
};

#endif // CONTROLLER_H

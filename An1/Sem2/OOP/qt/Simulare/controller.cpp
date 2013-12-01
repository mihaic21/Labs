#include "controller.h"

Controller::Controller(Repository* repo)
{
    this->repo = repo;
}

Controller::~Controller(){
    delete this->repo;
}

vector<Movie*> Controller::getAllMovies(){
    return this->repo->getAllMovies();
}

void Controller::changeAvailability(int id, bool available){
    this->repo->changeAvailability(id, available);
}

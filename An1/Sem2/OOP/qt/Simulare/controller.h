#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <vector>
#include "movie.h"
#include "repository.h"

class Controller
{
private:
    Repository* repo;
public:
    Controller(Repository* repo);
    ~Controller();

    /*
     * Controller method that returns the entire list of movies from the repository
     */
    vector<Movie*> getAllMovies();

    /*
     * Controller method that changes the availability of a movie
     */
    void changeAvailability(int id, bool available);
};

#endif // CONTROLLER_H

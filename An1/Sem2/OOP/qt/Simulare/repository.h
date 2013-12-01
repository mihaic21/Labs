#ifndef REPOSITORY_H
#define REPOSITORY_H

#include <iostream>
#include <vector>
#include "movie.h"
using namespace std;

#define REPOSITORY_SEPARATOR ';'
#define REPOSITORY_AVAILABLE_STRING "Yes"
#define REPOSITORY_UNAVAILABLE_STRING "No"

class Repository
{
private:
    /*
     * Filename of the repository file
     */
    string filename;

    /*
     * The list of movies
     */
    vector<Movie*> movieList;

    /*
     * Private method that populates the list of movies from the repository file
     */
    void readFromFile();

    /*
     * Private method that dumps the entire movie list to the repository file
     */
    void writeToFile();
public:
    Repository(string filename);
    ~Repository();

    /*
     * Method that returns the entire list of movies
     */
    vector<Movie*> getAllMovies();

    /*
     * Method that changes the availablity of a movie
     */
    void changeAvailability(int id, bool available);
};

#endif // REPOSITORY_H

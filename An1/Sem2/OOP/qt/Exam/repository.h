#ifndef REPOSITORY_H
#define REPOSITORY_H

#include <iostream>
#include <vector>
#include "dvd.h"
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
     * The list of DVDs
     */
    vector<DVD*> DVDList;

    /*
     * Private method that populates the list of DVDs from the repository file
     */
    void readFromFile();

    /*
     * Private method that dumps the entire DVD list to the repository file
     */
    void writeToFile();
public:
    Repository(string filename);
    ~Repository();

    /*
     * Method that returns the entire list of DVDs
     */
    vector<DVD*> getAllDVDs();

    /*
     * Method that changes the availablity of a DVD
     */
    void changeAvailability(int id, bool available);
};



#endif // REPOSITORY_H

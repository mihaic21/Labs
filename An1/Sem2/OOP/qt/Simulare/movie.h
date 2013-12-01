#ifndef DOMAIN_H
#define DOMAIN_H

#include <iostream>

class Movie
{
private:
    /*
     * Movie Title
     */
    std::string name;

    /*
     * Lead Actor in movie
     */
    std::string actor;

    /*
     * Movie availability
     */
    bool available;

public:
    Movie();
    Movie(std::string name, std::string actor, bool available);

    // Getters and setters for the variables
    void setName(std::string name);
    std::string getName();
    void setActor(std::string actor);
    std::string getActor();
    bool isAvailable();
    void setAvailable(bool available);

};

#endif // DOMAIN_H

#include <cassert>

#include "testmovie.h"
#include "movie.h"

testMovie::testMovie()
{
    Movie *movie = new Movie("NewName", "NewActor", true);
    assert(movie->getActor() == "NewActor");
    assert(movie->getName() == "NewName");
    assert(movie->isAvailable() == true);

    movie->setActor("Actor");
    assert(movie->getActor() == "Actor");
    movie->setName("Name");
    assert(movie->getName() == "Name");
    movie->setAvailable(false);
    assert(movie->isAvailable() == false);
}

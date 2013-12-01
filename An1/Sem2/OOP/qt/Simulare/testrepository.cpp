#include "testrepository.h"
#include "repository.h"
#include "movie.h"
#include <vector>
#include <cassert>

testRepository::testRepository()
{
    Repository *repo = new Repository("test.txt");
    vector<Movie*> list = repo->getAllMovies();

    assert(list[0]->getName() == "test");
    assert(list[0]->getActor() == "test");
    assert(list[0]->isAvailable() == true);

    assert(list[1]->getName() == "test2");
    assert(list[1]->getActor() == "test2");
    assert(list[1]->isAvailable() == true);

    assert(list[2]->getName() == "test3");
    assert(list[2]->getActor() == "test3");
    assert(list[2]->isAvailable() == false);

    repo->changeAvailability(2, true);

    assert(list[2]->isAvailable() == true);

    repo->changeAvailability(2, false);

}

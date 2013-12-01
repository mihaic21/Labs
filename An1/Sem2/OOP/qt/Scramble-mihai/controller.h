#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <QList>
#include "wordrepository.h"
#include "gamerepository.h"
#include "word.h"
#include "game.h"

class Controller
{
private:
    WordRepository *wordRepo;
    GameRepository *gameRepo;
public:
    Controller(WordRepository* wordRepo, GameRepository *gameRepo);
    Word getRandomWord();
    void archiveGame(Game* game);
};

#endif // CONTROLLER_H

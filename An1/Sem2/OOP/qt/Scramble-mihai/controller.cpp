#include <stdlib.h>
#include <time.h>
#include "controller.h"

Controller::Controller(WordRepository *wordRepo, GameRepository *gameRepo)
{
    this->wordRepo = wordRepo;
    this->gameRepo = gameRepo;
}

Word Controller::getRandomWord()
{
    QList<Word> allWords = this->wordRepo->getAll();
    srand(time(NULL));
    int randomNumber = rand() % allWords.size();
    return allWords[randomNumber];
}

void Controller::archiveGame(Game *game){
    this->gameRepo->archiveGame(game);
}

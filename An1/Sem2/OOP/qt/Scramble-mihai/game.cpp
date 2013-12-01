#include "game.h"

Game::Game(Word word, QString jumbledWord)
{
    this->word = word;
    this->score = 0;
    this->goingStrong = 0;
    this->jumbledWord = jumbledWord;
}

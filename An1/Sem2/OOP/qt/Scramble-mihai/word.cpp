#include "word.h"

Word::Word(QString word, int score)
{
    this->word = word;
    this->score = score;
}

QString Word::getWord()
{
    return this->word;
}

int Word::getScore()
{
    return this->score;
}

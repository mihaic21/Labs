#ifndef GAME_H
#define GAME_H

#include <QString>
#include <QList>
#include "word.h"

class Game
{
private:

public:
    Game(Word word, QString jumbledWord);
    QString jumbledWord;
    Word word;
    int score;
    int goingStrong;
    QList<QString> guessedWords;
};

#endif // GAME_H

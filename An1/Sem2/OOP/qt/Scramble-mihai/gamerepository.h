#ifndef GAMEREPOSITORY_H
#define GAMEREPOSITORY_H

#include <QString>
#include "game.h"

class GameRepository
{
private:
    QString filename;
public:
    GameRepository(QString filename);
    void archiveGame(Game* game);
};

#endif // GAMEREPOSITORY_H

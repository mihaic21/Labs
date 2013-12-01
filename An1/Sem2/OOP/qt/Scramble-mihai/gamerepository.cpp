#include <QFile>
#include <QTextStream>
#include "gamerepository.h"

GameRepository::GameRepository(QString filename)
{
    this->filename = filename;
}

void GameRepository::archiveGame(Game *game)
{
    QFile file(this->filename);
    file.open(QIODevice::Append|QIODevice::Text);

    QTextStream out(&file);

    out << game->score << ", " << game->guessedWords[0] << ", " << game->guessedWords[1] << ", " << game->guessedWords[2] << endl;

    file.close();
}

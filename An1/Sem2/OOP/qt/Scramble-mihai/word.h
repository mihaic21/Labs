#ifndef WORD_H
#define WORD_H

#include <QString>

class Word
{
private:
    QString word;
    int score;
public:
    Word(){};
    Word(QString word, int score);
    QString getWord();
    int getScore();
};

#endif // WORD_H

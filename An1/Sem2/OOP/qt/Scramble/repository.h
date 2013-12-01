#ifndef REPOSITORY_H
#define REPOSITORY_H

#include<QList>
#include<word.h>
#include<QLabel>
#include <iostream>
#include<fstream>

class repository
{
private:
    QList<Word> words;
public:
    repository();

    void readFromFile();
    void saveGameToFile(int score, vector<string> *guessed);

    QList<Word>* getAll();

};

#endif // REPOSITORY_H

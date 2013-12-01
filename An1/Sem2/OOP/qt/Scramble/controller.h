#ifndef CONTROLLER_H
#define CONTROLLER_H

#include<repository.h>

class controller
{
private:
    repository* repo;
public:
    controller(repository* repo);
    string scramble(string word);
    QList<Word>* getWords();
    int checkWord(string word);
    void saveToFile(int score,vector<string>* guessed);
};

#endif // CONTROLLER_H

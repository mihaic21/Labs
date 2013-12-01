#include "controller.h"

controller::controller(repository *repo)
{
    this->repo = repo;
}

QList<Word>* controller::getWords(){
    return this->repo->getAll();
}

int controller::checkWord(string word){
    /*
     *returns i if word is guessed
     *-1 otherwise
     */

    QList<Word>* all;
    all = this->repo->getAll();
    for(int i=0;i<all->size();i++){
        if(word == all->at(i).getWord())
            return i;
    }
    return 1;
}

string controller::scramble(string word){
    int x = word.length();
        for(int y = x; y > 0; y--)
        {
            int pos = rand()%x;
            char tmp = word[y-1];
            word[y-1] = word[pos];
            word[pos] = tmp;
        }
}

void controller::saveToFile(int score, vector<string> *guessed){
    this->repo->saveGameToFile(score,guessed);
}


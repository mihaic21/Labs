#include "repository.h"

repository::repository()
{
    this->readFromFile();
}

void repository::readFromFile(){
    ifstream file("words.txt");
    char word[20];
    string w;
    int points;
    int contor = 0;
    while(file>>word){
        contor++;
        if(contor == 1)
            w = word;
        if(contor == 2){
            points = atoi(word);
            Word wordd;
            wordd.setPoints(points);
            wordd.setWord(w);
            words.append(wordd);
            contor = 0;
        }
    }
    file.close();
}

QList<Word>* repository::getAll(){
    return &this->words;
}

void repository::saveGameToFile(int score, vector<string>* guessed){
    ofstream file("games.txt", ios::app);
    file<<"\nScore:"<<score<<" Words:";
    for(int i=0;i<guessed->size();i++)
        file<<guessed->at(i)<<" ";
    cout<<endl;
}


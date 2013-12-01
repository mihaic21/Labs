#ifndef WORD_H
#define WORD_H

#include <string>
#include<iostream>
using namespace std;

class Word{
private:
    string word;
    int points;
public:
    Word(){
        word = "";
        points = 0;
    }

    void setWord(string word){
        this->word = word;
    }

    void setPoints(int points){
        this->points = points;
    }

    string getWord() const {
        return this->word;
    }

    int getPoints() const{
        return this->points;
    }
};

#endif // WORD_H

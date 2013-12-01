#include "repository.h"

#include <QFile>
#include <QTextStream>
#include <fstream>
#include <sstream>


Repository::Repository(string filename)
{
    this->filename = filename;
    this->readFromFile();
}

Repository::~Repository(){
    for (int i=0; i<this->movieList.size(); i++){
        delete this->movieList[i];
    }
}

vector<string> split(const string& s, char separator){
    vector<string> tokens;
    stringstream ss(s);
    string item;
    while (getline(ss, item, separator)){
        tokens.push_back(item);
    }
    return tokens;
}

void Repository::readFromFile(){
    QFile file(QString::fromStdString(this->filename));
    file.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&file);
    while (!in.atEnd()){
        string line = in.readLine().toStdString();
        if (line != ""){
            string name, actor;
            bool available;
            vector<string> tokens = split(line, REPOSITORY_SEPARATOR);
            name = tokens[0];
            actor = tokens[1];
            if (tokens[2] == REPOSITORY_AVAILABLE_STRING){
                available = true;
            } else {
                available = false;
            }
            Movie *movie = new Movie(name, actor, available);
            this->movieList.push_back(movie);
        }
    }
    file.close();
}

void Repository::writeToFile(){
    QFile file(QString::fromStdString(this->filename));
    file.open(QIODevice::WriteOnly|QIODevice::Text);
    if (file.isOpen()){
        for (int i=0; i<this->movieList.size(); i++){
            QTextStream out(&file);
            out<<QString::fromStdString(this->movieList[i]->getName())<<REPOSITORY_SEPARATOR<<QString::fromStdString(this->movieList[i]->getActor())<<REPOSITORY_SEPARATOR;
            if (this->movieList[i]->isAvailable()){
                out<<REPOSITORY_AVAILABLE_STRING;
            } else {
                out<<REPOSITORY_UNAVAILABLE_STRING;
            }
            out << endl;
        }
    }
    file.close();
}

vector<Movie*> Repository::getAllMovies(){
    return this->movieList;
}

void Repository::changeAvailability(int id, bool available){
    this->movieList[id]->setAvailable(available);
    this->writeToFile();
}

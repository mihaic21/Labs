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
    for (int i=0; i<this->DVDList.size(); i++){
        delete this->DVDList[i];
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
            string name;
            int id;
            bool available;
            vector<string> tokens = split(line, REPOSITORY_SEPARATOR);
            istringstream A(tokens[0]);
            A>>id;
            name = tokens[1];
            if (tokens[2] == REPOSITORY_AVAILABLE_STRING){
                available = true;
            } else {
                available = false;
            }
            DVD* dvd = new DVD(id, name, available);
            this->DVDList.push_back(dvd);
        }
    }
    file.close();
}

void Repository::writeToFile(){
    QFile file(QString::fromStdString(this->filename));
    file.open(QIODevice::WriteOnly|QIODevice::Text);
    if (file.isOpen()){
        for (int i=0; i<this->DVDList.size(); i++){
            QTextStream out(&file);
            out<<this->DVDList[i]->getId()<<REPOSITORY_SEPARATOR<<QString::fromStdString(this->DVDList[i]->getName())<<REPOSITORY_SEPARATOR;
            if (this->DVDList[i]->isAvailable()){
                out<<REPOSITORY_AVAILABLE_STRING;
            } else {
                out<<REPOSITORY_UNAVAILABLE_STRING;
            }
            out << endl;
        }
    }
    file.close();
}

vector<DVD*> Repository::getAllDVDs(){
    return this->DVDList;
}

void Repository::changeAvailability(int id, bool available){
    this->DVDList[id]->setAvailable(available);
    this->writeToFile();
}



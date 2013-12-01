#include "repository.h"

Repository::Repository()
{
    loadFromFile();
}


void Repository::loadFromFile(){
    string name;
    string cnp;
    string media;
    float med;
    ifstream fisier("C:\\Qt\\Qt5.0.2\\Tools\\QtCreator\\bin\\Pregatire1\\in.txt");
    if (!fisier.is_open()){
        cout << "Failed to open!" << endl;
        exit(1);
    }
    while(fisier.good()){
        getline(fisier, name, ';');
        getline(fisier, cnp, ';');
        getline(fisier, media, '\n');
        med = atof(media.c_str());
        Student s(name, cnp, med);
        v.push_back(s);
    }
    v.pop_back();
    vector<Student>::iterator it;
    for (it = v.begin(); it != v.end(); it++){
        cout << *it;
    }
    fisier.close();
}

void Repository::writeToFile(){
    ofstream fisier("C:\\Qt\\Qt5.0.2\\Tools\\QtCreator\\bin\\Pregatire1\\in.txt");
    if (!fisier.is_open()){
        cout << "Failed to open!" << endl;
        exit(1);
    }
    vector<Student>::iterator it;
    for (it = v.begin(); it != v.end(); it++){
        fisier << *it;
    }
    fisier.close();
}

bool Repository::addStudent(Student s){
    if (find(v.begin(), v.end(), s) == v.end()){
        v.push_back(s);
        writeToFile();
    }else{
        throw(Exceptions("Deja este ba!!"));
    }
    return true;
}

bool Repository::removeStudent(Student s){
    vector<Student>::iterator it = find(v.begin(), v.end(), s);
    if (it != v.end()){
        v.erase(it);
        writeToFile();
    }else{
        throw(Exceptions("NU este ba!!"));
    }
    return true;
}

bool Repository::updateStudent(Student s){
    removeStudent(s);
    addStudent(s);
    writeToFile();
}

vector<Student> Repository::getAll(){
    return v;
}





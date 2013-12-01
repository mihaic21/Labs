#ifndef REPOSITORY_H
#define REPOSITORY_H

#include <vector>
#include <cstdlib>
#include <fstream>
#include <iostream>
#include <algorithm>
#include "../domain/student.h"
#include "../domain/exceptions.h"
using namespace std;

class Repository
{
    vector<Student> v;
    string filename;
public:

    Repository();
    Repository(string f): filename(f){}
    ~Repository(){}
    void loadFromFile();
    void writeToFile();
    bool addStudent(Student s);
    bool removeStudent(Student s);
    bool updateStudent(Student s);
    vector<Student> getAll();
};

#endif // REPOSITORY_H

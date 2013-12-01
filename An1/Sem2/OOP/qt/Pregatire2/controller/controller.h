#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "../repository/repository.h"
using namespace std;

class Controller
{
Repository repo;
public:
    Controller();
    void addStudent(Student s){ repo.addStudent(s);}
    void removeStudent(Student s){repo.removeStudent(s);}
    void updateStudent(Student s){repo.updateStudent(s);}
    vector<Student> getAll() {return repo.getAll();}
};

#endif // CONTROLLER_H

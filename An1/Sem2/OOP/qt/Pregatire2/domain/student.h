#ifndef STUDENT_H
#define STUDENT_H

#include <string>
#include <iostream>
#include <fstream>
using namespace std;

class Student
{
string name;
string cnp;
float media;
public:
Student(): name(""), cnp(""), media(0){}
Student(string n, string c, float m): name(n), cnp(c), media(m){}
    void setName(string n){name = n;}
    void setCnp(string n){cnp = n;}
    void setMedia(float m){media = m;}
    string getName(){return name;}
    string getCnp(){return cnp;}
    float getMedia(){return media;}
    Student &operator =(const Student &other){
        if (this != &other){
            name = other.name;
            cnp = other.cnp;
            media = other.media;
        }
        return *this;
    }
    friend bool operator == (Student s1, Student s2){
        return s1.cnp == s2.cnp;
    }
    friend ostream &operator << (ostream &os, Student &s){
        os << s.name << ";" << s.cnp << ";" << s.media << ";" << endl;
        return os;
    }

};

#endif // STUDENT_H

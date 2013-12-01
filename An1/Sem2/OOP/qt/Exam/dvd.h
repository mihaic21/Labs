#ifndef DVD_H
#define DVD_H

#include<iostream>

class DVD
{
private:
    //DVD ID
    int id;

    //DVD Name
    std::string name;

    //DVD availability
    bool available;

public:
    DVD();
    DVD(int id, std::string name, bool available);

    //Setters and getters for the variables
    void setName(std::string name);
    std::string getName();
    void setId(int id);
    int getId();
    bool isAvailable();
    void setAvailable(bool available);
};

#endif // DVD_H

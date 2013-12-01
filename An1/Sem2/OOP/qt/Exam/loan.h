#ifndef LOAN_H
#define LOAN_H
#include <QFile>
#include <QTextStream>

class Loan
{
private:
    //id of the lent DVD
    int id;

    //name of the person who borrowed the DVD
    std::string name;
public:
    Loan();
    Loan(int id, std::string name);

    //Getters and setters for the variables
    void setId(int id);
    int getId();
    void setName(std::string name);
    std::string getName();

};

#endif // LOAN_H

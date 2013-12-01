#ifndef LOAN_H
#define LOAN_H

#include <QString>

class Loan
{
public:
    Loan(QString personName, int dvdId);
    QString personName;
    int dvdId;
};

#endif // LOAN_H

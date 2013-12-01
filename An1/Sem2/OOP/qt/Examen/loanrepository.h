#ifndef LOANREPOSITORY_H
#define LOANREPOSITORY_H

#include <QList>
#include <QString>

#include "loan.h"

class LoanRepository
{
private:
    QList<Loan*> loans;
    QString filename;
    void readFromFile();
    void writeToFile();
public:
    LoanRepository(QString filename);
    QList<Loan*> getAll();
    void addLoan(Loan* loan);
    void removeLoan(int id);
    void updateLoan(Loan* loan, Loan* newLoan);
};

#endif // LOANREPOSITORY_H

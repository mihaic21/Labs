#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <QList>
#include <QString>
#include "dvd.h"
#include "loan.h"
#include "loanrepository.h"
#include "dvdrepository.h"

class Controller
{
private:
    DvdRepository* dvdRepo;
    LoanRepository* loanRepo;
public:
    Controller(DvdRepository* dvdRepo, LoanRepository* loanRepo);
    QList<Dvd*> getAllDvds();
    QList<Dvd*> getAllSortedDvds();
    QList<Loan*> getAllLoans();
    void addLoan(int id, QString name);
    void removeLoan(int id);
};

#endif // CONTROLLER_H

#include "loanrepository.h"

#include <QFile>
#include <QTextStream>
#include <QStringList>


LoanRepository::LoanRepository(QString filename)
{
    this->filename = filename;
    this->readFromFile();
}

void LoanRepository::readFromFile(){
    QFile file(this->filename);
    file.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&file);

    while (!in.atEnd()){
        QString line = in.readLine();
        QStringList sl = line.split('|');
        int id = sl[0].toInt();
        QString name = sl[1];
        Loan* loan = new Loan(name, id);
        this->loans.append(loan);
    }
    file.close();
}

void LoanRepository::writeToFile(){
    QFile file(this->filename);
    file.open(QIODevice::WriteOnly|QIODevice::Text);
    QTextStream out(&file);

    for (int i=0; i<this->loans.size(); i++){
        out << loans[i]->dvdId << '|' << loans[i]->personName << endl;
    }

    file.close();
}

QList<Loan*> LoanRepository::getAll(){
    return this->loans;
}

void LoanRepository::addLoan(Loan *loan){
    this->loans.append(loan);
    this->writeToFile();
}

void LoanRepository::removeLoan(int id){
    for (int i=0; i<this->loans.size(); i++){
        if (this->loans[i]->dvdId == id){
            this->loans.removeAt(i);
        }
    }
    this->writeToFile();
}

void LoanRepository::updateLoan(Loan *loan, Loan *newLoan){
    this->loans[this->loans.indexOf(loan)] = newLoan;
    this->writeToFile();
}

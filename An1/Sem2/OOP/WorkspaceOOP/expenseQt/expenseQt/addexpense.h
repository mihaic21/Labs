#ifndef ADDEXPENSE_H
#define ADDEXPENSE_H

#include <QDialog>
#include "domain/Validator.h"
#include "controller/Controller.h"
#include "mainwindow.h"
#include <QMainWindow>
#include <QString>

namespace Ui {
class addExpense;
}

class addExpense : public QDialog
{
    Q_OBJECT
    
public:
    explicit addExpense(QWidget *parent = 0);
    void addCtrl(Controller*);
    ~addExpense();
    
private slots:

    void on_cancel_clicked();

    void on_okay_clicked();

private:
    Ui::addExpense *ui;
    Validator validator;
    Controller *cont;
    void setError(QString);
    QObject *parentWindow;
};

#endif // ADDEXPENSE_H

#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QListWidgetItem>

#include "dvdrepository.h"
#include "loanrepository.h"
#include "controller.h"
#include "dvd.h"
#include "loan.h"

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT
    
public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();
    
private slots:
    void on_dvdList_itemClicked(QListWidgetItem *item);

    void on_lendDvd_clicked();

    void on_returnDvd_clicked();

private:
    Ui::Widget *ui;
    DvdRepository* dvdRepo;
    LoanRepository* loanRepo;
    Controller* controller;
    QList<Dvd*> allSortedDvds;
    QList<Loan*> loans;
};

#endif // WIDGET_H

#include "widget.h"
#include "ui_widget.h"

#include <QMessageBox>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->dvdRepo = new DvdRepository("dvds.txt");
    this->loanRepo = new LoanRepository("loans.txt");
    this->controller = new Controller(dvdRepo, loanRepo);

    this->allSortedDvds = this->controller->getAllSortedDvds();
    this->loans = this->controller->getAllLoans();

    for (int i=0; i<this->allSortedDvds.size(); i++){
        ui->dvdList->addItem(this->allSortedDvds[i]->getTitle());
    }

}

Widget::~Widget()
{
    delete this->dvdRepo;
    delete this->loanRepo;
    delete this->controller;
    delete ui;
}

void Widget::on_dvdList_itemClicked(QListWidgetItem *item)
{
    Dvd* selectedDvd = this->allSortedDvds[ui->dvdList->row(item)];
    ui->dvdBorrowedTo->clear();
    ui->dvdId->setText(QString::number(selectedDvd->getId()));
    ui->dvdName->setText(selectedDvd->getTitle());
    for (int i=0; i<this->loans.size(); i++){
        if (loans[i]->dvdId == selectedDvd->getId()){
            ui->dvdBorrowedTo->setText(loans[i]->personName);
            return;
        }
    }
}

void Widget::on_lendDvd_clicked()
{
    if (!ui->dvdBorrowedTo->text().isEmpty() && !ui->dvdList->selectedItems().isEmpty()){
        Dvd* selectedDvd = this->allSortedDvds[ui->dvdList->row(ui->dvdList->selectedItems()[0])];
        for (int i=0; i<this->loans.size(); i++){
            if (loans[i]->dvdId == selectedDvd->getId()){
                QMessageBox mb(this);
                mb.addButton(QMessageBox::Ok);
                mb.setIcon(QMessageBox::Critical);
                mb.setWindowModality(Qt::WindowModal);
                mb.setText("DVD already lent!");
                mb.exec();
                return;
            }
        }
        this->controller->addLoan(selectedDvd->getId(), ui->dvdBorrowedTo->text());
        this->loans = this->controller->getAllLoans();
    } else {
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("\"Borrowed By:\" field can not be empty");
        mb.exec();
        return;
    }
}

void Widget::on_returnDvd_clicked()
{
    if (!ui->dvdBorrowedTo->text().isEmpty() && !ui->dvdList->selectedItems().isEmpty()){
        Dvd* selectedDvd = this->allSortedDvds[ui->dvdList->row(ui->dvdList->selectedItems()[0])];
        this->controller->removeLoan(selectedDvd->getId());
        ui->dvdBorrowedTo->clear();
        this->loans = this->controller->getAllLoans();
    } else {
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("DVD is not lent!");
        mb.exec();
        return;
    }

}

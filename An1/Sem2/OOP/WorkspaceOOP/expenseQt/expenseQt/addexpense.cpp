#include "addexpense.h"
#include "ui_addexpense.h"
#include "domain/Exceptions.h"

addExpense::addExpense(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::addExpense)
{
    ui->setupUi(this);
    this->parentWindow = parent;
}

addExpense::~addExpense()
{
    delete ui;
}

void addExpense::addCtrl(Controller* cont){
    this->cont = cont;
}

void addExpense::on_cancel_clicked()
{
    this->close();
}

void addExpense::setError(QString errormsg){
    this->ui->error->setText("<font color='red'>Error: "+errormsg+"</font>");
}

void addExpense::on_okay_clicked()
{
    int id, day, amount;
    QString type;
    Expense *e;

    id = ui->idBox->text().toInt();
    day = ui->dayBox->text().toInt();
    amount = ui->amountBox->text().toInt();

    type = ui->comboBox->currentText();
    if(id < 1) { this->setError("Invalid ID"); return; }
    if(day < 1 || day > 31) { this->setError("Invalid day"); return; }
    if(amount < 0) { this->setError("Invalid amount"); return; }

    e = new Expense(id, day, amount, type.toStdString());

    try {
        this->cont->add(*e);
        ((MainWindow*)this->parentWindow)->refreshList();
    } catch (MyException &ex) {
        this->setError(QString::fromStdString(ex.getMsg()));
        return;
    }
    this->close();
}

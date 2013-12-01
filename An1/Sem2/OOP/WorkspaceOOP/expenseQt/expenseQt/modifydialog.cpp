#include "modifydialog.h"
#include "ui_modifydialog.h"

modifyDialog::modifyDialog(QWidget *parent, int id) :
    QDialog(parent),
    ui(new Ui::modifyDialog)
{
    ui->setupUi(this);
    this->parentWindow = parent;
    stringstream ss;
    ss << id;
    this->id = id;
    ui->id->setText("<font color='green'>Now editing entry "+QString::fromStdString(ss.str())+"</font>");
}


modifyDialog::~modifyDialog()
{
    delete ui;
}

void modifyDialog::addCtrl(Controller* cont){
    this->cont = cont;
}

void modifyDialog::on_cancelbtn_clicked()
{
    this->close();
}

void modifyDialog::setError(QString errormsg){
    this->ui->error->setText("<font color='red'>Error: "+errormsg+"</font>");
}

void modifyDialog::on_okbtn_clicked()
{
    if(this->id == 0)
        this->close();

    int day, amount;
    QString type;
    Expense *e;

    day = ui->dayBox->text().toInt();
    amount = ui->amountBox->text().toInt();
    type = ui->comboBox->currentText();

    if(day < 1 || day > 31) { this->setError("Invalid day"); return; }
    if(amount < 0) { this->setError("Invalid amount"); return; }

    e = new Expense(this->id, day, amount, type.toStdString());

    try {
        this->cont->update(this->id, *e);
        ((MainWindow*)this->parentWindow)->refreshList();
    } catch (MyException &ex) {
        this->setError(QString::fromStdString(ex.getMsg()));
        return;
    }
    this->close();
}

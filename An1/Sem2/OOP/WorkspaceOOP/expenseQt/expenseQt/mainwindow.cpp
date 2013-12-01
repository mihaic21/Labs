#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setFixedSize(this->width(),this->height());
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::refreshList(){
    vector<Expense*> all;
    string elem;
    ui->expenseList->clear();
    all = this->ctrl->getAll();
    for(unsigned i=0; i<all.size(); i++){
        elem = all[i]->print();
        ui->expenseList->addItem(QString::fromStdString(elem));
    }
    if(all.size()>0){
        this->showButtons();
        ui->expenseList->setCurrentRow(0);
    } else
        this->hideButtons();
}

void MainWindow::addCtrl(Controller *ctrl){
    this->ctrl = ctrl;
    this->refreshList();
}

void MainWindow::hideButtons(){
    ui->deleteExpense->setEnabled(false);
    ui->modifyExpense->setEnabled(false);
}

void MainWindow::showButtons(){
    ui->deleteExpense->setEnabled(true);
    ui->modifyExpense->setEnabled(true);
}

void MainWindow::on_allEntries_clicked()
{
    this->refreshList();
}

void MainWindow::addExpenseCtrl(Expense e){
    this->ctrl->add(e);
}

void MainWindow::on_addExpense_clicked()
{
    addExpense *add = new addExpense(this);
    add->addCtrl(this->ctrl);
    add->show();
}

void MainWindow::on_deleteExpense_clicked()
{
    int s = ui->expenseList->currentItem()->text().split(" ")[1].toInt();
    this->ctrl->remove(s);
    this->refreshList();
}

void MainWindow::on_modifyExpense_clicked()
{
    int s = ui->expenseList->currentItem()->text().split(" ")[1].toInt();
    modifyDialog *mod = new modifyDialog(this, s);
    mod->addCtrl(this->ctrl);
    mod->show();
}

void MainWindow::setError(QString errormsg){
    this->ui->error->setText("<font color='red'>Error: "+errormsg+"</font>");
}

void MainWindow::on_filterDay_clicked()
{
    bool ok;
    int day = ui->filterBox->text().toInt(&ok);
    if(!ok){
        this->setError("Value entered is not a number.");
        return;
    }

    vector<Expense*> all;
    string elem;
    ui->expenseList->clear();
    all = this->ctrl->filterByDay(day);
    for(unsigned i=0; i<all.size(); i++){
        elem = all[i]->print();
        ui->expenseList->addItem(QString::fromStdString(elem));
    }
    if(all.size()>0){
        this->showButtons();
        ui->expenseList->setCurrentRow(0);
    } else
        this->hideButtons();
}


void MainWindow::on_filterAmount_clicked()
{
    bool ok;
    int amount = ui->filterBox->text().toInt(&ok);
    if(!ok){
        this->setError("Value entered is not a number.");
        return;
    }

    vector<Expense*> all;
    string elem;
    ui->expenseList->clear();
    all = this->ctrl->filterByAmount(amount);
    for(unsigned i=0; i<all.size(); i++){
        elem = all[i]->print();
        ui->expenseList->addItem(QString::fromStdString(elem));
    }
    if(all.size()>0){
        this->showButtons();
        ui->expenseList->setCurrentRow(0);
    } else
        this->hideButtons();
}


void MainWindow::on_amountAsc_clicked()
{
    this->sortList("amount", "asc");
}


void MainWindow::on_amountDesc_clicked()
{
    this->sortList("amount", "desc");
}

void MainWindow::on_typeAsc_clicked()
{
    this->sortList("type", "asc");
}


void MainWindow::on_typeDesc_clicked()
{
    this->sortList("type", "desc");
}

void MainWindow::sortList(QString property, QString order){
    vector<Expense*> all;
    string elem;
    ui->expenseList->clear();

    if(property == "type"){
        if(order == "asc")
            all = this->ctrl->sortByTypeA();
        else
            all = this->ctrl->sortByTypeD();
    } else {
        if(order == "asc")
            all = this->ctrl->sortByAmountA();
        else
            all = this->ctrl->sortByAmountD();
    }

    for(unsigned i=0; i<all.size(); i++){
        elem = all[i]->print();
        ui->expenseList->addItem(QString::fromStdString(elem));
    }
    if(all.size()>0){
        this->showButtons();
        ui->expenseList->setCurrentRow(0);
    } else
        this->hideButtons();
}

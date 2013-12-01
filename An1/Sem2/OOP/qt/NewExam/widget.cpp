#include "widget.h"
#include "ui_widget.h"

#include <QMessageBox>
#include <QStringList>
#include <QString>
#include <QList>
#include "task.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->repo = new Repository("tasks.txt");
    QList<Task*> sortedList = this->getSortedTasks();
    for (int i=0; i<sortedList.size(); i++){
        QString str = sortedList[i]->getDescription() +", "+ sortedList[i]->getDate() +", "+ QString::number(sortedList[i]->getHours());
        ui->taskList->addItem(str);
        this->sum +=sortedList[i]->getHours();
    }
    ui->sum->setText(QString::number(this->sum));

}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_pushButton_clicked()
{
    if (ui->description->text().length() == 0 || ui->date->text().length() == 0 || ui->hours->text().length() == 0){
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("All fields must be non empty!");
        mb.exec();
        return;
    }


    bool condition = true;
    QString str = ui->date->text();
    QStringList sl = str.split(".");
    if (sl.length() != 3){
        condition = false;
    }

    if (sl[0].length() != 2 || sl[1].length() != 2 || sl[2].length() != 4){
        condition = false;
    }

    if (sl[0].toInt() == 0 || sl[1].toInt() == 0 | sl[2].toInt() == 0){
        condition = false;
    }

    if (!condition){
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("Invalid data format!");
        mb.exec();
        return;
    }

    if (ui->hours->text().toInt() <= 0) {
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("Invalid hour format!");
        mb.exec();
        return;
    }



    //Task *t = new Task(this->repo->getAll().length()+1, ui->description->text(), ui->date->text(), ui->hours->text().toInt());

    this->repo->addTask(this->repo->getAll().length()+1, ui->description->text(), ui->date->text(), ui->hours->text().toInt());
    ui->taskList->clear();
    this->sum = 0;
    QList<Task*> sortedList = this->getSortedTasks();
    for (int i=0; i<sortedList.size(); i++){
        QString str = sortedList[i]->getDescription() +", "+ sortedList[i]->getDate() +", "+ QString::number(sortedList[i]->getHours());
        ui->taskList->addItem(str);
        this->sum +=sortedList[i]->getHours();
    }
    ui->sum->setText(QString::number(this->sum));
}

bool lessThan (Task* one, Task* two){
    return one->getDate() < two->getDate();
}


QList<Task*> Widget::getSortedTasks(){
    QList<Task*> allTasks = this->repo->getAll();
    qSort(allTasks.begin(),allTasks.end(),lessThan);
    return allTasks;
}

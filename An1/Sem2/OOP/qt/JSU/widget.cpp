#include "widget.h"
#include "ui_widget.h"

#include <QString>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_pushButton_clicked()
{
    int nr1 = ui->number1->text().toInt();
    int nr2 = ui->number2->text().toInt();

    ui->result->setText(QString::number(nr1+nr2));

}

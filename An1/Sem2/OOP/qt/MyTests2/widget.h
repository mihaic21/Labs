#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include "repository.h"
#include "controller.h"
#include "test.h"

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
    void on_pushButton_clicked();

private:
    Ui::Widget *ui;
    Repository *repo;
    Controller *controller;
    int currentTest = 1;
    int currentQuestion = 1;
    int randomNumber;
    Test *test = new Test(this->currentTest);
};

#endif // WIDGET_H

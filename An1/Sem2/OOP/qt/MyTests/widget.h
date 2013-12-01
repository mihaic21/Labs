#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>

#include <QList>

#include "questionRepository.h"
#include "testRepository.h"
#include "controller.h"
#include "question.h"
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
    void on_next_clicked();

private:
    Ui::Widget *ui;
    QuestionRepository* questionRepo;
    TestRepository* testRepo;
    Controller* controller;
    void newTest();
    void newQuestion();
    int currentTest = 1;
    int questionNo = 0;
    int whichQuestion = 0;
    QList<Question*> questionList;
    QList<Test*> testList;
};

#endif // WIDGET_H

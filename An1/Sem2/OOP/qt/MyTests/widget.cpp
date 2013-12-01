#include "widget.h"
#include "ui_widget.h"
#include <QString>
#include <QLabel>
#include <QMessageBox>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->questionRepo=new QuestionRepository("questions.txt");
    this->testRepo=new TestRepository();
    this->controller=new Controller(questionRepo, testRepo);
    this->questionList=this->questionRepo->getAll();
    //this->whichQuestion=this->questionRepo->getAll().length();

    this->newTest();

}

void Widget::newTest(){
    QString thestring1, thestring2, thestring3;
    QString str = QString::number(currentTest);
    ui->currentTest->setText(str);

    Test theTest(this->currentTest);

    if (questionNo % 3 == 0){
        this->currentTest++;
        if (questionNo != 0){
            QString thestring = "Test ";
            QString thestring1 = QString::number(this->testList[this->currentTest-2]->getId());
            QString thestring2 = QString::number((this->testList[this->currentTest-2]->getCorrectAnswers()));
            QString thestring3 = QString::number(this->testList[this->currentTest-2]->getMaxQuestionCount());
//            thestring.append(thestring1);
//            thestring.append(": ");
//            thestring.append(thestring2);
//            thestring.append("/");
//            thestring.append(thestring3);
//            thestring.append("\n");
//            ui->previousTests->addItem(thestring);
            //ui->previousTests->addItem(QString::sprintf("Test %d: %d/%d\n", this->testList[this->currentTest-2]->getId(),this->testList[this->currentTest-2]->getCorrectAnswers(), this->testList[this->currentTest-2]->getMaxQuestionCount()));
            ui->previousTests->addItem("Test " + thestring1 + ": " + thestring2 + "/" + thestring3 + "\n");
        }

    }
    this->newQuestion();
    //this->newTest();
}

void Widget::newQuestion(){
    this->questionNo++;
    this->whichQuestion++;
    if (this->whichQuestion == this->questionRepo->getAll().length()+1){
        this->whichQuestion = 1;
    }
    ui->questionShow->clear();
    ui->questionShow->addItem(this->questionList[whichQuestion-1]->getText());
    QString str = QString::number(questionNo);
    ui->currentQuestion->setText(str);
}

void Widget::on_next_clicked(){
    QString answer = ui->theAnswer->text();
    if (answer.length() > 1){
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("Answer must only have one character!");
        mb.exec();
        return;
    }
    this->newQuestion();
}

Widget::~Widget()
{
    delete ui;
}


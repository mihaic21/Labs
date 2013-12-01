#include "widget.h"
#include "ui_widget.h"

#include <QMessageBox>
#include <time.h>
#include "test.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->repo = new Repository("questions.txt");
    this->controller = new Controller(repo);

    ui->labelCurrentTest->setText("Current Test: 1");
    ui->labelQuestion->setText("Question: 1");

    srand(time(NULL));
    this->randomNumber = rand () % this->repo->getAll().length();

    ui->currentQuestion->setText(this->repo->getAll()[this->randomNumber]->text);
}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_pushButton_clicked()
{
    if (ui->answer->text().length() != 1){
        QMessageBox mb(this);
        mb.addButton(QMessageBox::Ok);
        mb.setIcon(QMessageBox::Warning);
        mb.setWindowModality(Qt::WindowModal);
        mb.setText("Answer must have only one character!");
        mb.exec();
        return;
    } else {
        if (ui->answer->text() == this->repo->getAll()[this->randomNumber]->correctAnswer){
            this->test->correctAnswers++;
        }
        this->currentQuestion++;

        //inca ceva?
        if (this->currentQuestion > 3){
            this->currentTest++;
            this->currentQuestion = 1;
            QString str = "Current Test " + QString::number(this->currentTest);
            ui->labelCurrentTest->setText(str);
            str = "Question 1";
            ui->labelQuestion->setText(str);

            str = "Test "+QString::number(test->id)+": "+QString::number(test->correctAnswers)+"/"+QString::number(test->maxQuestionCount)+"\n";
            ui->previousTests->append(str);
            this->test->id++;
            this->test->correctAnswers = 0;
            this->test->questionCount = 0;
            }
        QString thestr = "Current Test: " + QString::number(this->currentTest);
        ui->labelCurrentTest->setText(thestr);
        thestr = "Question: " + QString::number(this->currentQuestion);
        ui->labelQuestion->setText(thestr);

        //srand(time(NULL));
        this->randomNumber = random () % this->repo->getAll().length();

        ui->currentQuestion->setText(this->repo->getAll()[this->randomNumber]->text);

        ui->answer->clear();
    }
}

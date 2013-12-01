#include "mainwindow.h"
#include <QApplication>
#include <QGridLayout>
#include <QListView>
#include <QLayout>
#include <QAbstractButton>

#include "domain/TestExpense.h"
#include "repository/testRepository.h"
#include "controller/testController.h"
#include "domain/TestValidator.h"

void test(){
    testExpense();
    testMRepository();
    testController();
    testValidator();
}

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;

    test();
    FileRepository* repo=new FileRepository("expenses.txt");
    Controller* cont=new Controller(repo);
    //UI* ui=new UI(cont);
    //ui->run();
    w.addCtrl(cont);
    w.show();
    
    return a.exec();
}

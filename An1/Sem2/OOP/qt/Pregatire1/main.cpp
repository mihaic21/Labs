
#include <QApplication>
#include "./domain/student.h"
#include "./repository/repository.h"
#include "./ui/ui.h"

int main(int argc, char *argv[])
{

    QApplication a(argc, argv);
    Ui w;
    w.show();
    
    return a.exec();
}


#include <QApplication>
#include "./domain/student.h"
#include "./repository/repository.h"
#include "./ui/ui.h"

int main(int argc, char *argv[])
{
    Student c;
    Student b("bla", "bla", 12);
    c = b;
    Repository r;
    Student s("space","nopls",10);
    r.addStudent(s);
    QApplication a(argc, argv);
    Ui w;
    w.show();
    
    return a.exec();
}

#ifndef UI_H
#define UI_H

#include <QMainWindow>
#include <QtGui>
#include <QtWidgets>
#include "../controller/controller.h"
#include "../repository/repository.h"
#include <QString>
#include <fstream>
#include <sstream>
#include <QSpinBox>
#include <QDoubleSpinBox>

class Ui: public QMainWindow
{
    Q_OBJECT

public:
    Ui();
    ~Ui();

private:
    Controller *co;
    QWidget *central;
    QPushButton *add;
    QPushButton *remove;
    QPushButton *update;
    QPushButton *refresh;
    QLabel *lname;
    QLabel *lcnp;
    QLabel *lmedia;
    QLineEdit *name;
    QLineEdit *cnp;
    QDoubleSpinBox *media;
    QListWidget *list;
    QHBoxLayout *listcont;
    QVBoxLayout *label;
    QVBoxLayout *linedit;
    QVBoxLayout *buttonscont;
    QHBoxLayout *cont;

    void initGui();
    void connectsig();
private slots:
    void handleadd();
    void handledelete();
    void handleupdate();
    void handlerefresh();
};

#endif // UI_H

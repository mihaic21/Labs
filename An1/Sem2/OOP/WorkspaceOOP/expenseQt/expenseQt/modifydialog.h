#ifndef MODIFYDIALOG_H
#define MODIFYDIALOG_H

#include <QDialog>
#include "controller/Controller.h"
#include "mainwindow.h"
#include <QMainWindow>
#include <QString>

namespace Ui {
class modifyDialog;
}

class modifyDialog : public QDialog
{
    Q_OBJECT
    
public:
    explicit modifyDialog(QWidget *parent = 0, int id = 0);
    void addCtrl(Controller*);
    ~modifyDialog();
    
private slots:
    void on_cancelbtn_clicked();

    void on_okbtn_clicked();

private:
    Controller *cont;
    void setError(QString);
    QObject *parentWindow;
    Ui::modifyDialog *ui;
    int id;
};

#endif // MODIFYDIALOG_H

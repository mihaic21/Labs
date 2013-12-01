#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QAbstractButton>
#include "controller/Controller.h"
#include "addexpense.h"
#include "modifydialog.h"
#include <QString>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    void addCtrl(Controller*);
    void addExpenseCtrl(Expense);
    void refreshList();
    
private slots:
    void on_allEntries_clicked();
    void on_addExpense_clicked();

    void on_deleteExpense_clicked();

    void on_modifyExpense_clicked();

    void on_filterDay_clicked();

    void on_filterAmount_clicked();

    void on_amountAsc_clicked();

    void on_amountDesc_clicked();

    void on_typeAsc_clicked();

    void on_typeDesc_clicked();

private:
    void sortList(QString, QString);
    void setError(QString);
    void hideButtons();
    void showButtons();
    Ui::MainWindow *ui;
    Controller *ctrl;
};

#endif // MAINWINDOW_H


#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include<controller.h>
#include<repository.h>
namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_MLIST_activated(const QModelIndex &index);

    void on_pushButton_clicked();

private:
    Ui::MainWindow *ui;
    Controller* ctl;
    void sortList();
};

#endif // MAINWINDOW_H

#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include "repository.h"
#include "task.h"
#include <QList>

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
    void on_pushButton_clicked();

private:
    Ui::Widget *ui;
    Repository *repo;
    int sum = 0;
    QList<Task*> getSortedTasks();
};

#endif // WIDGET_H

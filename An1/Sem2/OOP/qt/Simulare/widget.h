#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QListWidgetItem>
#include <vector>
#include "movie.h"
#include "repository.h"
#include "controller.h"

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
    void on_movieListWidget_itemClicked(QListWidgetItem *item);

    void on_rent_clicked();

    void on_return_2_clicked();

private:
    Ui::Widget *ui;
    Controller* controller;
};

#endif // WIDGET_H

#include "widget.h"
#include "ui_widget.h"
#include "testmovie.h"
#include "testrepository.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    ui->available->clear();
/*
    testMovie *tm = new testMovie();
    delete tm;

    testRepository *tr = new testRepository();
    delete tr;
*/
    Repository *repo = new Repository("movies.txt");
    this->controller = new Controller(repo);

    vector<Movie*> list = this->controller->getAllMovies();
    for (int i=0; i<list.size(); i++){
        ui->movieListWidget->addItem(QString::fromStdString(list[i]->getName()));
    }

}

Widget::~Widget()
{
    delete ui;
    delete this->controller;
}

void Widget::on_movieListWidget_itemClicked(QListWidgetItem *item)
{
    vector<Movie*> list = this->controller->getAllMovies();
    int index = ui->movieListWidget->row(item);
    ui->actor->setText(QString::fromStdString(list[index]->getActor()));
    ui->title->setText(QString::fromStdString(list[index]->getName()));
    if (list[index]->isAvailable()){
        ui->available->setText("Yes");
    } else {
        ui->available->setText("No");
    }
}

void Widget::on_rent_clicked()
{
    int index = -1;
    index = ui->movieListWidget->row(ui->movieListWidget->selectedItems()[0]);
    if (index >= 0){
        this->controller->changeAvailability(index, false);
        this->on_movieListWidget_itemClicked(ui->movieListWidget->selectedItems()[0]);
    }
}

void Widget::on_return_2_clicked()
{
    int index = -1;
    index = ui->movieListWidget->row(ui->movieListWidget->selectedItems()[0]);
    if (index >= 0){
        this->controller->changeAvailability(index, true);
        this->on_movieListWidget_itemClicked(ui->movieListWidget->selectedItems()[0]);
    }
}

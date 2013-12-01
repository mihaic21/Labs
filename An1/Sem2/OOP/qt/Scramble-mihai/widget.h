#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>

#include "wordrepository.h"
#include "gamerepository.h"
#include "controller.h"
#include "game.h"

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
    void on_submit_clicked();

private:
    Ui::Widget *ui;
    WordRepository *wordRepo;
    GameRepository *gameRepo;
    Controller *controller;
    Game *game;
    void startNewGame();
    void refreshScoreLabel();
    void refreshWordLabel();
    void getNewWord();
};

#endif // WIDGET_H

#include <QList>
#include <QListWidget>
#include <iostream>
#include <algorithm>
#include "widget.h"
#include "ui_widget.h"
#include "word.h"

void jumbleLetters(QString &w){
    std::random_shuffle(w.begin(), w.end());
}

void Widget::getNewWord()
{
    Word word = this->controller->getRandomWord();
    QString jumbledWord = word.getWord();
    jumbleLetters(jumbledWord);

    this->game->word = word;
    this->game->jumbledWord = jumbledWord;
}

void Widget::startNewGame()
{
    if (this->game != NULL){
        delete this->game;
    }

    this->game = new Game(Word("word", 0), "jumbledWord");
    this->getNewWord();
}

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->game = NULL;
    this->wordRepo = new WordRepository("words.txt");
    this->gameRepo = new GameRepository("games.txt");
    this->controller = new Controller(this->wordRepo, this->gameRepo);

    this->startNewGame();

    this->refreshWordLabel();
}

Widget::~Widget()
{
    delete ui;
    delete this->wordRepo;
    delete this->controller;
}

void Widget::refreshScoreLabel(){
    QString text = "Score: ";
    text.append(QString::number(this->game->score));
    ui->score->setText(text);
}

void Widget::refreshWordLabel(){
    ui->word->setText(this->game->jumbledWord);
}

void Widget::on_submit_clicked()
{
    if (ui->lineEdit->text().compare(this->game->word.getWord()) == 0){
        this->game->score += this->game->word.getScore();
        this->game->goingStrong++;
        ui->listWidget->addItem(this->game->word.getWord());
        this->game->guessedWords.append(this->game->word.getWord());
        if (this->game->goingStrong == 3){
            this->controller->archiveGame(this->game);
            this->startNewGame();
        } else {
            this->getNewWord();
        }
    } else {
        this->game->score--;
        this->game->goingStrong = 0;
        this->game->guessedWords.clear();
    }

    ui->lineEdit->clear();
    this->refreshScoreLabel();
    this->refreshWordLabel();

}

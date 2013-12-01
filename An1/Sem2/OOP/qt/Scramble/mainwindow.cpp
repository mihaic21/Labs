#include "mainwindow.h"

MainWindow::MainWindow(QWidget *parent, controller* ctr)
    : QMainWindow(parent)
{
    this->score = 0;
    this->rand = 0;
    this->current = 0;
    repository* repo = new repository;
    this->ctr = new controller(repo);
    this->setupUI();
    this->wordToGuess();
}

MainWindow::~MainWindow()
{
}

void MainWindow::setupUI(){
    resize(600,400);
    this->layout = new QGridLayout;
    this->widget = new QWidget;
    widget->setLayout(layout);

    this->guessField = new QLineEdit;
    this->previousGames = new QListWidget;
    this->layout->addWidget(previousGames,3,0,1,1);
    this->guessButton = new QPushButton("&Guess");
    this->layout->addWidget(guessField,2,0);
    this->layout->addWidget(guessButton,2,1);
    this->guessButton->setDefault(true);
    this->scrambled = new QLineEdit;
    this->scrambledLabel = new QLabel("Word to guess:");
    this->scoreLabel = new QLabel("Current score:");
    this->scoreField = new QLineEdit;
    this->layout->addWidget(scrambledLabel,0,0);
    this->layout->addWidget(scrambled,0,1);
    this->layout->addWidget(scoreLabel,1,0);
    this->layout->addWidget(scoreField,1,1);
    QObject::connect(guessButton,SIGNAL(clicked()),this,SLOT(onGuessButtonClicked()));
    this->widget->show();
    this->updateScore();
}

void MainWindow::wordToGuess(){
    QList<Word>* all;
    all = this->ctr->getWords();
    QString line;
    string w = all->at(this->rand).getWord();
    std::random_shuffle(w.begin(),w.end());
    line = QString::fromStdString(w);
    this->scrambled->setText(line);
}

void MainWindow::onGuessButtonClicked(){
    string guess = this->guessField->text().toStdString();
    QList<Word>* all;
    all = this->ctr->getWords();
    int index = ctr->checkWord(guess);
    int index2;
    index2 = ctr->checkWord(all->at(rand).getWord());
    if(index != index2)
        this->score--;
    else{
        this->score+=all->at(rand).getPoints();
        QString line;
        line = QString::fromStdString(all->at(rand).getWord());
        this->previousGames->addItem(line);
        this->words.push_back(all->at(rand).getWord());
        this->randomize();
        this->wordToGuess();
        current++;
    }
    if(current == 3){
        this->previousGames->clear();
        this->ctr->saveToFile(this->score,&this->words);
        this->current = 0;
        this->score = 0;
        this->words.clear();
    }
    this->updateScore();
    this->guessField->clear();

}

void MainWindow::updateScore(){
    QString s;
    s.setNum(this->score);
    this->scoreField->setText(s);
}

void MainWindow::randomize(){
    QList<Word>* all;
    all = this->ctr->getWords();
    if(this->rand == all->size()-1)
        rand = 0;
    else
        rand++;
}

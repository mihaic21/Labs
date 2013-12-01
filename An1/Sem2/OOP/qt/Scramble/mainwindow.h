#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include"controller.h"
#include<QPushButton>
#include<QWidget>
#include<QGridLayout>
#include<QLineEdit>
#include<QListWidget>
#include<QLabel>

class MainWindow : public QMainWindow
{
    Q_OBJECT
private:
    controller* ctr;
    int score;
    int rand;
    void setupUI();
    int current;
    std::vector<string> words;
    void wordToGuess();
    void guessed();
public:
    MainWindow(QWidget *parent = 0, controller* ctr = 0);
    ~MainWindow();

    QGridLayout* layout;
    QWidget* widget;
    QPushButton* guessButton;
    QLineEdit* scoreField;
    QListWidget* previousGames;
    QLabel* scoreLabel;
    QLabel* scrambledLabel;
    QLineEdit* guessField;
    QLineEdit* scrambled;

    void updateScore();
    void randomize();
private slots:
    void onGuessButtonClicked();
};

#endif // MAINWINDOW_H

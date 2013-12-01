/********************************************************************************
** Form generated from reading UI file 'widget.ui'
**
** Created by: Qt User Interface Compiler version 5.0.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_WIDGET_H
#define UI_WIDGET_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Widget
{
public:
    QListWidget *questionShow;
    QLabel *label;
    QLabel *label_2;
    QLabel *label_3;
    QLineEdit *theAnswer;
    QPushButton *next;
    QLabel *label_4;
    QListWidget *previousTests;
    QLabel *currentTest;
    QLabel *currentQuestion;

    void setupUi(QWidget *Widget)
    {
        if (Widget->objectName().isEmpty())
            Widget->setObjectName(QStringLiteral("Widget"));
        Widget->resize(462, 402);
        questionShow = new QListWidget(Widget);
        questionShow->setObjectName(QStringLiteral("questionShow"));
        questionShow->setGeometry(QRect(20, 70, 421, 141));
        label = new QLabel(Widget);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(20, 20, 91, 17));
        label_2 = new QLabel(Widget);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(20, 40, 71, 17));
        label_3 = new QLabel(Widget);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(30, 230, 67, 17));
        theAnswer = new QLineEdit(Widget);
        theAnswer->setObjectName(QStringLiteral("theAnswer"));
        theAnswer->setGeometry(QRect(90, 220, 161, 27));
        next = new QPushButton(Widget);
        next->setObjectName(QStringLiteral("next"));
        next->setGeometry(QRect(290, 220, 141, 27));
        label_4 = new QLabel(Widget);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setGeometry(QRect(30, 260, 101, 17));
        previousTests = new QListWidget(Widget);
        previousTests->setObjectName(QStringLiteral("previousTests"));
        previousTests->setGeometry(QRect(20, 280, 421, 101));
        currentTest = new QLabel(Widget);
        currentTest->setObjectName(QStringLiteral("currentTest"));
        currentTest->setGeometry(QRect(120, 20, 67, 17));
        currentQuestion = new QLabel(Widget);
        currentQuestion->setObjectName(QStringLiteral("currentQuestion"));
        currentQuestion->setGeometry(QRect(100, 40, 67, 17));

        retranslateUi(Widget);

        QMetaObject::connectSlotsByName(Widget);
    } // setupUi

    void retranslateUi(QWidget *Widget)
    {
        Widget->setWindowTitle(QApplication::translate("Widget", "Widget", 0));
        label->setText(QApplication::translate("Widget", "Current test:", 0));
        label_2->setText(QApplication::translate("Widget", "Question: ", 0));
        label_3->setText(QApplication::translate("Widget", "Answer:", 0));
        next->setText(QApplication::translate("Widget", "Next question", 0));
        label_4->setText(QApplication::translate("Widget", "Previous tests", 0));
        currentTest->setText(QApplication::translate("Widget", "TextLabel", 0));
        currentQuestion->setText(QApplication::translate("Widget", "TextLabel", 0));
    } // retranslateUi

};

namespace Ui {
    class Widget: public Ui_Widget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_WIDGET_H

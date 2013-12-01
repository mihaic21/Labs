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
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Widget
{
public:
    QLabel *labelCurrentTest;
    QLabel *labelQuestion;
    QTextEdit *currentQuestion;
    QLabel *label_3;
    QLineEdit *answer;
    QPushButton *pushButton;
    QTextEdit *previousTests;
    QLabel *label_4;

    void setupUi(QWidget *Widget)
    {
        if (Widget->objectName().isEmpty())
            Widget->setObjectName(QStringLiteral("Widget"));
        Widget->resize(484, 371);
        labelCurrentTest = new QLabel(Widget);
        labelCurrentTest->setObjectName(QStringLiteral("labelCurrentTest"));
        labelCurrentTest->setGeometry(QRect(30, 10, 151, 17));
        labelQuestion = new QLabel(Widget);
        labelQuestion->setObjectName(QStringLiteral("labelQuestion"));
        labelQuestion->setGeometry(QRect(30, 30, 161, 17));
        currentQuestion = new QTextEdit(Widget);
        currentQuestion->setObjectName(QStringLiteral("currentQuestion"));
        currentQuestion->setGeometry(QRect(50, 60, 371, 81));
        label_3 = new QLabel(Widget);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(30, 150, 67, 17));
        answer = new QLineEdit(Widget);
        answer->setObjectName(QStringLiteral("answer"));
        answer->setGeometry(QRect(120, 150, 113, 27));
        pushButton = new QPushButton(Widget);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(270, 150, 131, 27));
        previousTests = new QTextEdit(Widget);
        previousTests->setObjectName(QStringLiteral("previousTests"));
        previousTests->setGeometry(QRect(80, 260, 291, 81));
        label_4 = new QLabel(Widget);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setGeometry(QRect(30, 240, 141, 17));

        retranslateUi(Widget);

        QMetaObject::connectSlotsByName(Widget);
    } // setupUi

    void retranslateUi(QWidget *Widget)
    {
        Widget->setWindowTitle(QApplication::translate("Widget", "Widget", 0));
        labelCurrentTest->setText(QApplication::translate("Widget", "TextLabel", 0));
        labelQuestion->setText(QApplication::translate("Widget", "TextLabel", 0));
        label_3->setText(QApplication::translate("Widget", "Answer:", 0));
        pushButton->setText(QApplication::translate("Widget", "Next Question", 0));
        label_4->setText(QApplication::translate("Widget", "Previous Tests:", 0));
    } // retranslateUi

};

namespace Ui {
    class Widget: public Ui_Widget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_WIDGET_H

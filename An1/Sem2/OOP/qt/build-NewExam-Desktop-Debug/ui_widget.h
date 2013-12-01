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
    QListWidget *taskList;
    QLabel *sum;
    QLabel *label;
    QLineEdit *description;
    QLabel *label_2;
    QLabel *label_3;
    QLabel *label_4;
    QLineEdit *hours;
    QLineEdit *date;
    QPushButton *pushButton;
    QLabel *label_5;

    void setupUi(QWidget *Widget)
    {
        if (Widget->objectName().isEmpty())
            Widget->setObjectName(QStringLiteral("Widget"));
        Widget->resize(496, 404);
        taskList = new QListWidget(Widget);
        taskList->setObjectName(QStringLiteral("taskList"));
        taskList->setGeometry(QRect(30, 40, 221, 291));
        sum = new QLabel(Widget);
        sum->setObjectName(QStringLiteral("sum"));
        sum->setGeometry(QRect(140, 370, 81, 17));
        label = new QLabel(Widget);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(290, 30, 101, 31));
        description = new QLineEdit(Widget);
        description->setObjectName(QStringLiteral("description"));
        description->setGeometry(QRect(290, 100, 171, 27));
        label_2 = new QLabel(Widget);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(290, 70, 101, 17));
        label_3 = new QLabel(Widget);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(290, 150, 67, 17));
        label_4 = new QLabel(Widget);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setGeometry(QRect(290, 230, 67, 17));
        hours = new QLineEdit(Widget);
        hours->setObjectName(QStringLiteral("hours"));
        hours->setGeometry(QRect(290, 260, 171, 27));
        date = new QLineEdit(Widget);
        date->setObjectName(QStringLiteral("date"));
        date->setGeometry(QRect(290, 180, 171, 27));
        pushButton = new QPushButton(Widget);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(290, 310, 171, 27));
        label_5 = new QLabel(Widget);
        label_5->setObjectName(QStringLiteral("label_5"));
        label_5->setGeometry(QRect(90, 370, 41, 17));

        retranslateUi(Widget);

        QMetaObject::connectSlotsByName(Widget);
    } // setupUi

    void retranslateUi(QWidget *Widget)
    {
        Widget->setWindowTitle(QApplication::translate("Widget", "Widget", 0));
        sum->setText(QApplication::translate("Widget", "sumnr", 0));
        label->setText(QApplication::translate("Widget", "Add New Task:", 0));
        label_2->setText(QApplication::translate("Widget", "Description:", 0));
        label_3->setText(QApplication::translate("Widget", "Date:", 0));
        label_4->setText(QApplication::translate("Widget", "Hours:", 0));
        pushButton->setText(QApplication::translate("Widget", "Add Task", 0));
        label_5->setText(QApplication::translate("Widget", "Sum:", 0));
    } // retranslateUi

};

namespace Ui {
    class Widget: public Ui_Widget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_WIDGET_H

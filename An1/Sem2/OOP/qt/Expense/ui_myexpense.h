/********************************************************************************
** Form generated from reading UI file 'myexpense.ui'
**
** Created: Sat 22. Jun 10:31:08 2013
**      by: Qt User Interface Compiler version 4.8.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MYEXPENSE_H
#define UI_MYEXPENSE_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MyExpenseClass
{
public:

    void setupUi(QWidget *MyExpenseClass)
    {
        if (MyExpenseClass->objectName().isEmpty())
            MyExpenseClass->setObjectName(QString::fromUtf8("MyExpenseClass"));
        MyExpenseClass->resize(400, 300);

        retranslateUi(MyExpenseClass);

        QMetaObject::connectSlotsByName(MyExpenseClass);
    } // setupUi

    void retranslateUi(QWidget *MyExpenseClass)
    {
        MyExpenseClass->setWindowTitle(QApplication::translate("MyExpenseClass", "MyExpense", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class MyExpenseClass: public Ui_MyExpenseClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MYEXPENSE_H

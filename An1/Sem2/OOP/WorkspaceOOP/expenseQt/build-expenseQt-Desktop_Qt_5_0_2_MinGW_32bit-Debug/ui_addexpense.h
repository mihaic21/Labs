/********************************************************************************
** Form generated from reading UI file 'addexpense.ui'
**
** Created by: Qt User Interface Compiler version 5.0.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ADDEXPENSE_H
#define UI_ADDEXPENSE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_addExpense
{
public:
    QLabel *error;
    QWidget *layoutWidget;
    QGridLayout *gridLayout_2;
    QGridLayout *gridLayout;
    QLineEdit *idBox;
    QLineEdit *amountBox;
    QLineEdit *dayBox;
    QLabel *label_7;
    QLabel *label_3;
    QLabel *label;
    QLabel *label_2;
    QComboBox *comboBox;
    QHBoxLayout *horizontalLayout;
    QPushButton *okay;
    QPushButton *cancel;

    void setupUi(QDialog *addExpense)
    {
        if (addExpense->objectName().isEmpty())
            addExpense->setObjectName(QStringLiteral("addExpense"));
        addExpense->resize(257, 209);
        error = new QLabel(addExpense);
        error->setObjectName(QStringLiteral("error"));
        error->setGeometry(QRect(20, 180, 211, 20));
        error->setAlignment(Qt::AlignCenter);
        layoutWidget = new QWidget(addExpense);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(21, 11, 211, 161));
        gridLayout_2 = new QGridLayout(layoutWidget);
        gridLayout_2->setObjectName(QStringLiteral("gridLayout_2"));
        gridLayout_2->setContentsMargins(0, 0, 0, 0);
        gridLayout = new QGridLayout();
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        idBox = new QLineEdit(layoutWidget);
        idBox->setObjectName(QStringLiteral("idBox"));

        gridLayout->addWidget(idBox, 0, 1, 1, 1);

        amountBox = new QLineEdit(layoutWidget);
        amountBox->setObjectName(QStringLiteral("amountBox"));

        gridLayout->addWidget(amountBox, 2, 1, 1, 1);

        dayBox = new QLineEdit(layoutWidget);
        dayBox->setObjectName(QStringLiteral("dayBox"));

        gridLayout->addWidget(dayBox, 1, 1, 1, 1);

        label_7 = new QLabel(layoutWidget);
        label_7->setObjectName(QStringLiteral("label_7"));

        gridLayout->addWidget(label_7, 0, 0, 1, 1);

        label_3 = new QLabel(layoutWidget);
        label_3->setObjectName(QStringLiteral("label_3"));

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        label = new QLabel(layoutWidget);
        label->setObjectName(QStringLiteral("label"));

        gridLayout->addWidget(label, 1, 0, 1, 1);

        label_2 = new QLabel(layoutWidget);
        label_2->setObjectName(QStringLiteral("label_2"));

        gridLayout->addWidget(label_2, 3, 0, 1, 1);

        comboBox = new QComboBox(layoutWidget);
        comboBox->setObjectName(QStringLiteral("comboBox"));

        gridLayout->addWidget(comboBox, 3, 1, 1, 1);


        gridLayout_2->addLayout(gridLayout, 0, 0, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        okay = new QPushButton(layoutWidget);
        okay->setObjectName(QStringLiteral("okay"));

        horizontalLayout->addWidget(okay);

        cancel = new QPushButton(layoutWidget);
        cancel->setObjectName(QStringLiteral("cancel"));

        horizontalLayout->addWidget(cancel);


        gridLayout_2->addLayout(horizontalLayout, 1, 0, 1, 1);


        retranslateUi(addExpense);

        QMetaObject::connectSlotsByName(addExpense);
    } // setupUi

    void retranslateUi(QDialog *addExpense)
    {
        addExpense->setWindowTitle(QApplication::translate("addExpense", "Add new expense", 0));
        error->setText(QString());
        label_7->setText(QApplication::translate("addExpense", "ID:", 0));
        label_3->setText(QApplication::translate("addExpense", "Amount", 0));
        label->setText(QApplication::translate("addExpense", "Day:", 0));
        label_2->setText(QApplication::translate("addExpense", "Type", 0));
        comboBox->clear();
        comboBox->insertItems(0, QStringList()
         << QApplication::translate("addExpense", "food", 0)
         << QApplication::translate("addExpense", "housekeeping", 0)
         << QApplication::translate("addExpense", "telephone", 0)
         << QApplication::translate("addExpense", "transport", 0)
         << QApplication::translate("addExpense", "clothing", 0)
         << QApplication::translate("addExpense", "others", 0)
        );
        okay->setText(QApplication::translate("addExpense", "Ok", 0));
        cancel->setText(QApplication::translate("addExpense", "Cancel", 0));
    } // retranslateUi

};

namespace Ui {
    class addExpense: public Ui_addExpense {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ADDEXPENSE_H

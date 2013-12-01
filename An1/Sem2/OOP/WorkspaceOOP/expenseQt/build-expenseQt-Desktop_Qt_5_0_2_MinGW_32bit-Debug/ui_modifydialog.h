/********************************************************************************
** Form generated from reading UI file 'modifydialog.ui'
**
** Created by: Qt User Interface Compiler version 5.0.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MODIFYDIALOG_H
#define UI_MODIFYDIALOG_H

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

class Ui_modifyDialog
{
public:
    QLabel *id;
    QWidget *layoutWidget;
    QGridLayout *gridLayout_2;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *dayBox;
    QLabel *label_2;
    QLineEdit *amountBox;
    QLabel *label_3;
    QComboBox *comboBox;
    QHBoxLayout *horizontalLayout;
    QPushButton *okbtn;
    QPushButton *cancelbtn;
    QLabel *error;

    void setupUi(QDialog *modifyDialog)
    {
        if (modifyDialog->objectName().isEmpty())
            modifyDialog->setObjectName(QStringLiteral("modifyDialog"));
        modifyDialog->resize(322, 209);
        id = new QLabel(modifyDialog);
        id->setObjectName(QStringLiteral("id"));
        id->setGeometry(QRect(10, 10, 311, 20));
        id->setAlignment(Qt::AlignCenter);
        layoutWidget = new QWidget(modifyDialog);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(30, 40, 261, 121));
        gridLayout_2 = new QGridLayout(layoutWidget);
        gridLayout_2->setObjectName(QStringLiteral("gridLayout_2"));
        gridLayout_2->setContentsMargins(0, 0, 0, 0);
        gridLayout = new QGridLayout();
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        label = new QLabel(layoutWidget);
        label->setObjectName(QStringLiteral("label"));

        gridLayout->addWidget(label, 0, 0, 1, 1);

        dayBox = new QLineEdit(layoutWidget);
        dayBox->setObjectName(QStringLiteral("dayBox"));

        gridLayout->addWidget(dayBox, 0, 1, 1, 1);

        label_2 = new QLabel(layoutWidget);
        label_2->setObjectName(QStringLiteral("label_2"));

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        amountBox = new QLineEdit(layoutWidget);
        amountBox->setObjectName(QStringLiteral("amountBox"));

        gridLayout->addWidget(amountBox, 1, 1, 1, 1);

        label_3 = new QLabel(layoutWidget);
        label_3->setObjectName(QStringLiteral("label_3"));

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        comboBox = new QComboBox(layoutWidget);
        comboBox->setObjectName(QStringLiteral("comboBox"));

        gridLayout->addWidget(comboBox, 2, 1, 1, 1);


        gridLayout_2->addLayout(gridLayout, 0, 0, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        okbtn = new QPushButton(layoutWidget);
        okbtn->setObjectName(QStringLiteral("okbtn"));

        horizontalLayout->addWidget(okbtn);

        cancelbtn = new QPushButton(layoutWidget);
        cancelbtn->setObjectName(QStringLiteral("cancelbtn"));

        horizontalLayout->addWidget(cancelbtn);


        gridLayout_2->addLayout(horizontalLayout, 1, 0, 1, 1);

        error = new QLabel(modifyDialog);
        error->setObjectName(QStringLiteral("error"));
        error->setGeometry(QRect(30, 180, 261, 20));
        error->setAlignment(Qt::AlignCenter);

        retranslateUi(modifyDialog);

        QMetaObject::connectSlotsByName(modifyDialog);
    } // setupUi

    void retranslateUi(QDialog *modifyDialog)
    {
        modifyDialog->setWindowTitle(QApplication::translate("modifyDialog", "Modify Expense", 0));
        id->setText(QApplication::translate("modifyDialog", "TextLabel", 0));
        label->setText(QApplication::translate("modifyDialog", "Day:", 0));
        label_2->setText(QApplication::translate("modifyDialog", "Amount:", 0));
        label_3->setText(QApplication::translate("modifyDialog", "Type:", 0));
        comboBox->clear();
        comboBox->insertItems(0, QStringList()
         << QApplication::translate("modifyDialog", "food", 0)
         << QApplication::translate("modifyDialog", "housekeeping", 0)
         << QApplication::translate("modifyDialog", "telephone", 0)
         << QApplication::translate("modifyDialog", "transport", 0)
         << QApplication::translate("modifyDialog", "clothing", 0)
         << QApplication::translate("modifyDialog", "others", 0)
        );
        okbtn->setText(QApplication::translate("modifyDialog", "OK", 0));
        cancelbtn->setText(QApplication::translate("modifyDialog", "Cancel", 0));
        error->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class modifyDialog: public Ui_modifyDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MODIFYDIALOG_H

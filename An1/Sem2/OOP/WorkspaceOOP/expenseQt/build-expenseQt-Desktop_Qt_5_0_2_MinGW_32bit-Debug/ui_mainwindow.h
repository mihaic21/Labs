/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.0.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QListWidget *expenseList;
    QLabel *error;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout_3;
    QFormLayout *formLayout;
    QPushButton *modifyExpense;
    QPushButton *addExpense;
    QPushButton *deleteExpense;
    QPushButton *allEntries;
    QSpacerItem *verticalSpacer;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QLineEdit *filterBox;
    QHBoxLayout *horizontalLayout;
    QPushButton *filterDay;
    QPushButton *filterAmount;
    QSpacerItem *verticalSpacer_2;
    QVBoxLayout *verticalLayout_2;
    QLabel *label_2;
    QGridLayout *gridLayout;
    QLabel *label_3;
    QLabel *label_4;
    QPushButton *amountAsc;
    QPushButton *typeAsc;
    QPushButton *amountDesc;
    QPushButton *typeDesc;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(463, 501);
        QSizePolicy sizePolicy(QSizePolicy::Fixed, QSizePolicy::Fixed);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(MainWindow->sizePolicy().hasHeightForWidth());
        MainWindow->setSizePolicy(sizePolicy);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        sizePolicy.setHeightForWidth(centralWidget->sizePolicy().hasHeightForWidth());
        centralWidget->setSizePolicy(sizePolicy);
        expenseList = new QListWidget(centralWidget);
        expenseList->setObjectName(QStringLiteral("expenseList"));
        expenseList->setGeometry(QRect(9, 9, 271, 451));
        error = new QLabel(centralWidget);
        error->setObjectName(QStringLiteral("error"));
        error->setGeometry(QRect(-40, 400, 441, 20));
        error->setAlignment(Qt::AlignCenter);
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(290, 10, 162, 351));
        verticalLayout_3 = new QVBoxLayout(layoutWidget);
        verticalLayout_3->setSpacing(6);
        verticalLayout_3->setContentsMargins(11, 11, 11, 11);
        verticalLayout_3->setObjectName(QStringLiteral("verticalLayout_3"));
        verticalLayout_3->setContentsMargins(0, 0, 0, 0);
        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QStringLiteral("formLayout"));
        formLayout->setFieldGrowthPolicy(QFormLayout::AllNonFixedFieldsGrow);
        formLayout->setLabelAlignment(Qt::AlignCenter);
        formLayout->setFormAlignment(Qt::AlignHCenter|Qt::AlignTop);
        modifyExpense = new QPushButton(layoutWidget);
        modifyExpense->setObjectName(QStringLiteral("modifyExpense"));

        formLayout->setWidget(1, QFormLayout::FieldRole, modifyExpense);

        addExpense = new QPushButton(layoutWidget);
        addExpense->setObjectName(QStringLiteral("addExpense"));

        formLayout->setWidget(1, QFormLayout::LabelRole, addExpense);

        deleteExpense = new QPushButton(layoutWidget);
        deleteExpense->setObjectName(QStringLiteral("deleteExpense"));

        formLayout->setWidget(2, QFormLayout::FieldRole, deleteExpense);

        allEntries = new QPushButton(layoutWidget);
        allEntries->setObjectName(QStringLiteral("allEntries"));

        formLayout->setWidget(2, QFormLayout::LabelRole, allEntries);


        verticalLayout_3->addLayout(formLayout);

        verticalSpacer = new QSpacerItem(20, 40, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout_3->addItem(verticalSpacer);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        label = new QLabel(layoutWidget);
        label->setObjectName(QStringLiteral("label"));
        label->setAlignment(Qt::AlignCenter);

        verticalLayout->addWidget(label);

        filterBox = new QLineEdit(layoutWidget);
        filterBox->setObjectName(QStringLiteral("filterBox"));

        verticalLayout->addWidget(filterBox);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        filterDay = new QPushButton(layoutWidget);
        filterDay->setObjectName(QStringLiteral("filterDay"));

        horizontalLayout->addWidget(filterDay);

        filterAmount = new QPushButton(layoutWidget);
        filterAmount->setObjectName(QStringLiteral("filterAmount"));

        horizontalLayout->addWidget(filterAmount);


        verticalLayout->addLayout(horizontalLayout);


        verticalLayout_3->addLayout(verticalLayout);

        verticalSpacer_2 = new QSpacerItem(20, 40, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout_3->addItem(verticalSpacer_2);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        label_2 = new QLabel(layoutWidget);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setAlignment(Qt::AlignCenter);

        verticalLayout_2->addWidget(label_2);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        gridLayout->setContentsMargins(0, 0, 0, 0);
        label_3 = new QLabel(layoutWidget);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setAlignment(Qt::AlignCenter);

        gridLayout->addWidget(label_3, 0, 0, 1, 1);

        label_4 = new QLabel(layoutWidget);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setAlignment(Qt::AlignCenter);

        gridLayout->addWidget(label_4, 0, 1, 1, 1);

        amountAsc = new QPushButton(layoutWidget);
        amountAsc->setObjectName(QStringLiteral("amountAsc"));

        gridLayout->addWidget(amountAsc, 1, 0, 1, 1);

        typeAsc = new QPushButton(layoutWidget);
        typeAsc->setObjectName(QStringLiteral("typeAsc"));

        gridLayout->addWidget(typeAsc, 1, 1, 1, 1);

        amountDesc = new QPushButton(layoutWidget);
        amountDesc->setObjectName(QStringLiteral("amountDesc"));

        gridLayout->addWidget(amountDesc, 2, 0, 1, 1);

        typeDesc = new QPushButton(layoutWidget);
        typeDesc->setObjectName(QStringLiteral("typeDesc"));

        gridLayout->addWidget(typeDesc, 2, 1, 1, 1);


        verticalLayout_2->addLayout(gridLayout);


        verticalLayout_3->addLayout(verticalLayout_2);

        MainWindow->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "Family Expense Manager", 0));
        error->setText(QString());
        modifyExpense->setText(QApplication::translate("MainWindow", "Modify", 0));
        addExpense->setText(QApplication::translate("MainWindow", "Add new", 0));
        deleteExpense->setText(QApplication::translate("MainWindow", "Delete", 0));
        allEntries->setText(QApplication::translate("MainWindow", "See all ", 0));
        label->setText(QApplication::translate("MainWindow", "Filter by:", 0));
        filterDay->setText(QApplication::translate("MainWindow", "Day", 0));
        filterAmount->setText(QApplication::translate("MainWindow", "Amount", 0));
        label_2->setText(QApplication::translate("MainWindow", "Sort By:", 0));
        label_3->setText(QApplication::translate("MainWindow", "Amount", 0));
        label_4->setText(QApplication::translate("MainWindow", "Type", 0));
        amountAsc->setText(QApplication::translate("MainWindow", "Ascending", 0));
        typeAsc->setText(QApplication::translate("MainWindow", "Ascending", 0));
        amountDesc->setText(QApplication::translate("MainWindow", "Descending", 0));
        typeDesc->setText(QApplication::translate("MainWindow", "Descending", 0));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H

/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.0.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Widget
{
public:
    QWidget *layoutWidget;
    QHBoxLayout *horizontalLayout_2;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QListWidget *dvdList;
    QVBoxLayout *verticalLayout_2;
    QLabel *label_2;
    QLineEdit *dvdId;
    QLabel *label_3;
    QLineEdit *dvdName;
    QLabel *label_4;
    QLineEdit *dvdBorrowedTo;
    QHBoxLayout *horizontalLayout;
    QPushButton *lendDvd;
    QPushButton *returnDvd;

    void setupUi(QWidget *Widget)
    {
        if (Widget->objectName().isEmpty())
            Widget->setObjectName(QStringLiteral("Widget"));
        Widget->resize(611, 385);
        layoutWidget = new QWidget(Widget);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(70, 50, 431, 292));
        horizontalLayout_2 = new QHBoxLayout(layoutWidget);
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_2->setObjectName(QStringLiteral("horizontalLayout_2"));
        horizontalLayout_2->setContentsMargins(0, 0, 0, 0);
        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        label = new QLabel(layoutWidget);
        label->setObjectName(QStringLiteral("label"));

        verticalLayout->addWidget(label);

        dvdList = new QListWidget(layoutWidget);
        dvdList->setObjectName(QStringLiteral("dvdList"));

        verticalLayout->addWidget(dvdList);


        horizontalLayout_2->addLayout(verticalLayout);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        label_2 = new QLabel(layoutWidget);
        label_2->setObjectName(QStringLiteral("label_2"));

        verticalLayout_2->addWidget(label_2);

        dvdId = new QLineEdit(layoutWidget);
        dvdId->setObjectName(QStringLiteral("dvdId"));

        verticalLayout_2->addWidget(dvdId);

        label_3 = new QLabel(layoutWidget);
        label_3->setObjectName(QStringLiteral("label_3"));

        verticalLayout_2->addWidget(label_3);

        dvdName = new QLineEdit(layoutWidget);
        dvdName->setObjectName(QStringLiteral("dvdName"));

        verticalLayout_2->addWidget(dvdName);

        label_4 = new QLabel(layoutWidget);
        label_4->setObjectName(QStringLiteral("label_4"));

        verticalLayout_2->addWidget(label_4);

        dvdBorrowedTo = new QLineEdit(layoutWidget);
        dvdBorrowedTo->setObjectName(QStringLiteral("dvdBorrowedTo"));

        verticalLayout_2->addWidget(dvdBorrowedTo);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        lendDvd = new QPushButton(layoutWidget);
        lendDvd->setObjectName(QStringLiteral("lendDvd"));

        horizontalLayout->addWidget(lendDvd);

        returnDvd = new QPushButton(layoutWidget);
        returnDvd->setObjectName(QStringLiteral("returnDvd"));

        horizontalLayout->addWidget(returnDvd);


        verticalLayout_2->addLayout(horizontalLayout);


        horizontalLayout_2->addLayout(verticalLayout_2);


        retranslateUi(Widget);

        QMetaObject::connectSlotsByName(Widget);
    } // setupUi

    void retranslateUi(QWidget *Widget)
    {
        Widget->setWindowTitle(QApplication::translate("Widget", "Widget", 0));
        label->setText(QApplication::translate("Widget", "DVDs:", 0));
        label_2->setText(QApplication::translate("Widget", "Id:", 0));
        label_3->setText(QApplication::translate("Widget", "Name:", 0));
        label_4->setText(QApplication::translate("Widget", "Borrowed by:", 0));
        lendDvd->setText(QApplication::translate("Widget", "Lend", 0));
        returnDvd->setText(QApplication::translate("Widget", "Return", 0));
    } // retranslateUi

};

namespace Ui {
    class Widget: public Ui_Widget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H

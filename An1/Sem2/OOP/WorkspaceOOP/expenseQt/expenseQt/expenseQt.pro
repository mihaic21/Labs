#-------------------------------------------------
#
# Project created by QtCreator 2013-05-20T19:09:50
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = expenseQt
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    utils/utils.cpp \
    ui/UI.cpp \
    repository/testRepository.cpp \
    repository/MemRepository.cpp \
    repository/FileRepository.cpp \
    domain/Validator.cpp \
    domain/testValidator.cpp \
    domain/TestExpense.cpp \
    domain/Expense.cpp \
    controller/testController.cpp \
    controller/Controller.cpp \
    addexpense.cpp \
    modifydialog.cpp

HEADERS  += mainwindow.h \
    utils/utils.h \
    ui/UI.h \
    repository/testRepository.h \
    repository/Node.h \
    repository/MemRepository.h \
    repository/List.h \
    repository/FileRepository.h \
    domain/Validator.h \
    domain/testValidator.h \
    domain/TestExpense.h \
    domain/Expense.h \
    domain/Exceptions.h \
    controller/testController.h \
    controller/Controller.h \
    addexpense.h \
    modifydialog.h

FORMS    += mainwindow.ui \
    addexpense.ui \
    modifydialog.ui

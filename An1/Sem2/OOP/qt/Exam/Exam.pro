#-------------------------------------------------
#
# Project created by QtCreator 2013-06-29T10:04:25
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Exam
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    dvd.cpp \
    repository.cpp \
    controller.cpp \
    loan.cpp

HEADERS  += mainwindow.h \
    dvd.h \
    repository.h \
    controller.h \
    loan.h

FORMS    += mainwindow.ui

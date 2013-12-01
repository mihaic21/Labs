#-------------------------------------------------
#
# Project created by QtCreator 2013-06-29T10:56:14
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = dvdBun
TEMPLATE = app


SOURCES += main.cpp\
        widget.cpp \
    controller.cpp \
    dvd.cpp \
    dvdrepository.cpp \
    loan.cpp \
    loanrepository.cpp

HEADERS  += widget.h \
    controller.h \
    dvd.h \
    dvdrepository.h \
    loan.h \
    loanrepository.h

FORMS    += widget.ui

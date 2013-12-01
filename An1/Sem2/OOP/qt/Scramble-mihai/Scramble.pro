#-------------------------------------------------
#
# Project created by QtCreator 2013-06-25T11:53:23
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Scramble
TEMPLATE = app


SOURCES += main.cpp\
        widget.cpp \
    wordrepository.cpp \
    word.cpp \
    controller.cpp \
    game.cpp \
    gamerepository.cpp

HEADERS  += widget.h \
    wordrepository.h \
    word.h \
    controller.h \
    game.h \
    gamerepository.h

FORMS    += widget.ui

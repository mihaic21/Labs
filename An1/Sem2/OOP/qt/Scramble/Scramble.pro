#-------------------------------------------------
#
# Project created by QtCreator 2013-06-26T16:10:49
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Scramble
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    controller.cpp \
    repository.cpp

HEADERS  += mainwindow.h \
    controller.h \
    guess.h \
    word.h \
    repository.h

FORMS    += mainwindow.ui

OTHER_FILES += \
    ../build-Scramble-Desktop-Debug/words.txt \
    ../build-Scramble-Desktop-Debug/games.txt

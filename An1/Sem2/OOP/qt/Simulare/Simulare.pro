#-------------------------------------------------
#
# Project created by QtCreator 2013-06-04T12:05:30
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Simulare
TEMPLATE = app


SOURCES += main.cpp\
        widget.cpp \
    controller.cpp \
    repository.cpp \
    movie.cpp \
    testmovie.cpp \
    testrepository.cpp

HEADERS  += widget.h \
    controller.h \
    repository.h \
    movie.h \
    testmovie.h \
    testrepository.h

FORMS    += \
    widget.ui

OTHER_FILES +=

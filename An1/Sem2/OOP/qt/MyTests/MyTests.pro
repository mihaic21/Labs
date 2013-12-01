#-------------------------------------------------
#
# Project created by QtCreator 2013-07-09T13:40:26
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = MyTests
TEMPLATE = app


SOURCES += main.cpp\
        widget.cpp \
    test.cpp \
    question.cpp \
    questionRepository.cpp \
    testRepository.cpp \
    controller.cpp

HEADERS  += widget.h \
    test.h \
    question.h \
    questionRepository.h \
    testRepository.h \
    controller.h

FORMS    += widget.ui

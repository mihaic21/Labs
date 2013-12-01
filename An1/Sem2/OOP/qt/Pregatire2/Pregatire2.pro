#-------------------------------------------------
#
# Project created by QtCreator 2013-06-02T12:15:22
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Pregatire2
TEMPLATE = app


SOURCES += main.cpp\
    controller/controller.cpp \
    domain/student.cpp \
    domain/exceptions.cpp \
    repository/repository.cpp \
    ui/ui.cpp

HEADERS  += \
    controller/controller.h \
    domain/student.h \
    domain/exceptions.h \
    repository/repository.h \
    ui/ui.h


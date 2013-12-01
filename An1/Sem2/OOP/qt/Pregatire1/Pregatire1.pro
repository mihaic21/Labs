#-------------------------------------------------
#
# Project created by QtCreator 2013-06-01T16:46:10
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Pregatire1
TEMPLATE = app


SOURCES += main.cpp\
    domain/student.cpp \
    repository/repository.cpp \
    domain/exceptions.cpp \
    controller/controller.cpp \
    ui/ui.cpp

HEADERS  += \
    domain/student.h \
    repository/repository.h \
    domain/exceptions.h \
    controller/controller.h \
    ui/ui.h

FORMS    +=

OTHER_FILES += \
    in.txt \
    out.txt

TEMPLATE = app
TARGET = Expense
QT += core \
    gui
HEADERS += src/Controller/expenseManager.h \
    src/Repository/testRepository.h \
    src/Utils/list.h \
    src/Utils/utils.h \
    src/Utils/vector.h \
    src/Repository/expenseRepository.h \
    src/Domain/testExpense.h \
    src/Domain/expense.h \
    myexpense.h
SOURCES += src/Controller/expenseManager.cpp \
    src/Repository/testRepo.cpp \
    src/Repository/expenseRepository.cpp \
    src/Utils/utils.cpp \
    src/Domain/testExpense.cpp \
    main.cpp \
    myexpense.cpp
FORMS += myexpense.ui
RESOURCES += 

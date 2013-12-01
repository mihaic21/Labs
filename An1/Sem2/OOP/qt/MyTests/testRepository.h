#ifndef TESTREPOSITORY_H
#define TESTREPOSITORY_H

#include <QString>
#include <QList>
#include "test.h"

class TestRepository
{
private:
    QList<Test> testList;
public:
    TestRepository(){};
    void archieveTest(Test* test);
};

#endif // TESTREPOSITORY_H

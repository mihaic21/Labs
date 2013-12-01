#include <QTextStream>
#include <QFile>

#include "testRepository.h"
/*
TestRepository::TestRepository(QString filename){
    this->filename=filename;
}

void TestRepository::archieveTest(Test *test){
    QFile file(this->filename);
    file.open(QIODevice::Append|QIODevice::Text);

    QTextStream out(&file);

    out << test->getId() << ", " << test->getCorrectAnswers() << ", " << test->getQuestionCount() << ", " << test->getMaxQuestionCount() << endl;

    file.close();
}
*/

#ifndef TEST_H
#define TEST_H

class Test
{
public:
    Test();
    Test(int id);
    int id;
    int correctAnswers;
    int questionCount;
    int maxQuestionCount;
};

#endif // TEST_H

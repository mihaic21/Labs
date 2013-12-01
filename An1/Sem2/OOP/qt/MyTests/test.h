#ifndef TEST_H
#define TEST_H

class Test
{
private:
    int id;
    int correctAnswers;
    int questionCount;
    int maxQuestionCount = 3;
public:
    Test (){};
    Test (int id);
    int getQuestionCount();
    void incrementQuestionCount();
    int getCorrectAnswers();
    void incrementCorrectAnswers();
    int getId();
    int getMaxQuestionCount();
};

#endif // TEST_H

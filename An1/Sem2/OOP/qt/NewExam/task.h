#ifndef TASK_H
#define TASK_H

#include <QString>

class Task
{
private:
    int id;
    QString description;
    QString date;
    int hours;
public:
    Task();
    Task(int id, QString description, QString date, int hours);

    void setId(int id);
    void setDescription(QString description);
    void setDate(QString date);
    void setHours(int id);

    int getId();
    QString getDescription();
    QString getDate();
    int getHours();


};

#endif // TASK_H

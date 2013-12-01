#ifndef DVD_H
#define DVD_H

#include <QString>


class Dvd
{
private:
    int id;
    QString title;
public:
    Dvd();
    Dvd(int id, QString title);
    int getId();
    QString getTitle();
};

#endif // DVD_H

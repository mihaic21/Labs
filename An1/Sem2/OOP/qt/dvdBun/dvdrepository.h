#ifndef DVDREPOSITORY_H
#define DVDREPOSITORY_H

#include <QList>
#include <QString>
#include "dvd.h"

class DvdRepository
{
private:
    QList<Dvd*> dvds;
    QString filename;
    void readFromFile();
public:
    DvdRepository(QString filename);
    QList<Dvd*> getAllDvds();
};

#endif // DVDREPOSITORY_H

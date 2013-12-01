#include "dvdrepository.h"

#include <QFile>
#include <QTextStream>
#include <QStringList>

DvdRepository::DvdRepository(QString filename)
{
    this->filename = filename;
    this->readFromFile();
}

void DvdRepository::readFromFile(){
    QFile file(this->filename);
    file.open(QIODevice::ReadOnly|QIODevice::Text);
    QTextStream in(&file);

    while (!in.atEnd()) {
       int id;
       QString title;
       QString line = in.readLine();
       QStringList sl = line.split('-');
       id = sl[0].toInt();
       title = sl[1];
       Dvd *dvd = new Dvd(id, title);
       this->dvds.append(dvd);
    }
    file.close();

}

QList<Dvd*> DvdRepository::getAllDvds(){
    return this->dvds;
}


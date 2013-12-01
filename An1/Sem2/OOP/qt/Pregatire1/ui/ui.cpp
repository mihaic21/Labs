#include "ui.h"

Ui::Ui()
{
    initGui();
    handlerefresh();
    connectsig();
}

void Ui::initGui(){
    central = new QWidget;
    add = new QPushButton;
    remove = new QPushButton;
    update = new QPushButton;
    refresh = new QPushButton;
    lname = new QLabel;
    lcnp = new QLabel;
    lmedia = new QLabel;
    name = new QLineEdit;
    cnp = new QLineEdit;
    media = new QDoubleSpinBox;
    list = new QListWidget;
    listcont = new QHBoxLayout;
    label = new QVBoxLayout;
    linedit = new QVBoxLayout;
    buttonscont = new QVBoxLayout;
    cont = new QHBoxLayout;
    setCentralWidget(central);
    //init
    add->setText("Add");
    remove->setText("Remove");
    update->setText("Update");
    refresh->setText("Refresh");
    lname->setText("Name: ");
    lcnp->setText("CNP: ");
    lmedia->setText("Media: ");
    media->setRange(1,10);
    listcont->addWidget(list);
    buttonscont->addWidget(add);
    buttonscont->addWidget(remove);
    buttonscont->addWidget(update);
    buttonscont->addWidget(refresh);
    listcont->addLayout(buttonscont);
    label->addWidget(lname);
    label->addWidget(lcnp);
    label->addWidget(lmedia);
    linedit->addWidget(name);
    linedit->addWidget(cnp);
    linedit->addWidget(media);
    cont->addLayout(listcont);
    cont->addLayout(label);
    cont->addLayout(linedit);
    central->setLayout(cont);
    co = new Controller;
}

Ui::~Ui(){
}

void Ui::handlerefresh(){
    list->clear();
    vector<Student> v = co->getAll();
    vector<Student>::iterator it;
    for (it = v.begin(); it != v.end(); it++){
        string s1 = it->getName()+ " ";
        s1 = s1+it->getCnp()+" ";
        ostringstream buffer;
        buffer << it->getMedia();
        string str = buffer.str();
        s1 = s1+str;
        QString s= QString::fromStdString(s1);
        list->addItem(s);
    }
}
void Ui::handleadd(){
    string n = name->text().toStdString();
    string c = cnp->text().toStdString();
    float m = media->value();
    //float a = atol(m.c_str());
    try{
        Student s(n, c, m);
        co->addStudent(s);
    }catch (Exceptions &ex){
        QString qs = QString::fromStdString(ex.getMessage());
        QMessageBox::information(this, "Info", qs);
    }
}


void Ui::handledelete(){
    string n = name->text().toStdString();
    string c = cnp->text().toStdString();
    float m = media->value();
    try{
        Student s(n, c, m);
        co->removeStudent(s);
    }catch (Exceptions &ex){
        QString qs = QString::fromStdString(ex.getMessage());
        QMessageBox::information(this, "Info", qs);
    }
}


void Ui::handleupdate(){
    string n = name->text().toStdString();
    string c = cnp->text().toStdString();
    float m = media->value();
    try{
        Student s(n, c, m);
        co->updateStudent(s);
    }catch (Exceptions &ex){
        QString qs = QString::fromStdString(ex.getMessage());
        QMessageBox::information(this, "Info", qs);
    }

}

void Ui::connectsig(){
    connect(add, SIGNAL(clicked()), this, SLOT(handleadd()));
    connect(refresh, SIGNAL(clicked()), this, SLOT(handlerefresh()));
    connect(remove, SIGNAL(clicked()), this, SLOT(handledelete()));
    connect(update,SIGNAL(clicked()), this, SLOT(handleupdate()));
}

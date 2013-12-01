//
//  Medication.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Medication.h"

Medication::Medication(int id, string name, int concentration, int quantity):Substance(name, concentration){
    this->id = id;
    this->quantity = quantity;
}

Medication::Medication():Substance(){
    this->id = 0;
    this->quantity = 0;
}

int Medication::getId(){
    return this->id;
}

void Medication::setId(int id){
    this->id = id;
}

int Medication::getQuantity(){
    return this->quantity;
}

void Medication::setQuantity(int quantity){
    this->quantity = quantity;
}

ostream& operator<< (ostream& os, Medication& med){
    Substance *sub = &med;
    os << med.id << " | " << sub->getName() << " | " << sub->getConcentration() << " | " << med.quantity << endl;
    return os;
}

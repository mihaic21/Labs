//
//  Sale.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 10/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Sale.h"

Sale::Sale(){
    
}

Sale::Sale(Medication* medication, int date, int quantity){
    this->medication = medication;
    this->date = date;
    this->quantity = quantity;
}

int Sale::getId(){
    return this->id;
}

void Sale::setId(int id){
    this->id = id;
}

int Sale::getDate(){
    return this->date;
}

void Sale::setDate(int date){
    this->date = date;
}

int Sale::getQuantity(){
    return this->quantity;
}

void Sale::setQuantity(int quantity){
    this->quantity = quantity;
}

Medication* Sale::getMedication(){
    return this->medication;
}

ostream& operator<< (ostream& os, Sale& sale){
    os << sale.getId() << " | " << sale.getDate() << " | " << sale.getQuantity() << endl;
    return os;
}

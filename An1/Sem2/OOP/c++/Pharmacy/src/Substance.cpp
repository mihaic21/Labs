//
//  Substance.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 10/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Substance.h"

Substance::Substance(){
    this->name = "";
    this->concentration = 0;
}

Substance::Substance(string name, int concentration){
    this->name = name;
    this->concentration = concentration;
}

string Substance::getName(){
    return this->name;
}

void Substance::setName(string name){
    this->name = name;
}

int Substance::getConcentration(){
    return this->concentration;
}

void Substance::setConcentration(int concentration){
    this->concentration = concentration;
}
//
//  TestMedication.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <cassert>

#include "TestMedication.h"


TestMedication::TestMedication(){
    this->runTests();
}

void TestMedication::testId(){
    Medication *med = new Medication(0,"a",20,20);
    med->setId(1);
    assert(med->getId() == 1);
    delete med;
}

void TestMedication::testName(){
    Medication *med = new Medication(0,"a",20,20);
    med->setName("b");
    assert(med->getName() == "b");
    delete med;
}

void TestMedication::testConcentration(){
    Medication *med = new Medication(0,"a",20,20);
    med->setConcentration(40);
    assert(med->getConcentration() == 40);
    delete med;
}

void TestMedication::testQuantity(){
    Medication *med = new Medication(0,"a",20,20);
    med->setQuantity(40);
    assert(med->getQuantity() == 40);
    delete med;
}

void TestMedication::runTests(){
    this->testId();
    this->testName();
    this->testConcentration();
    this->testQuantity();
    
}
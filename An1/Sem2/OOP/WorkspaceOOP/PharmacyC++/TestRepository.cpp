//
//  TestRepository.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <cassert>
#include <vector>
#include <fstream>

#include "TestRepository.h"


TestRepository::TestRepository(){
    fstream file;
    file.open("tests.txt", ios::out);
    file.close();
    this->repo = new Repository("tests.txt");
    this->runTests();
}

void TestRepository::testAddNewMedication(){
    Medication *med = new Medication(0,"a", 20, 20);
    this->repo->addNewMedication(*med);
    vector<Medication> all = this->repo->listMedication();
    assert(all[0].getId() == 0);
    assert(all[0].getName() == "a");
    assert(all[0].getConcentration() == 20);
    assert(all[0].getQuantity() == 20);
    delete med;
    med = new Medication(1,"b", 40, 40);
    this->repo->addNewMedication(*med);
    all = this->repo->listMedication();
    assert(all[1].getId() == 1);
    assert(all[1].getName() == "b");
    assert(all[1].getConcentration() == 40);
    assert(all[1].getQuantity() == 40);
    delete med;
}

void TestRepository::testDeleteMedication(){
    assert(this->repo->deleteMedication(2) != 0);
    this->repo->deleteMedication(0);
    vector<Medication> all = this->repo->listMedication();
    assert(all[0].getId() != 0);
    assert(all[0].getName() != "a");
    assert(all[0].getConcentration() != 20);
    assert(all[0].getQuantity() != 20);
}

void TestRepository::testUpdateMedication(){
    this->repo->updateMedication(1, "a", 20, 20);
    vector<Medication> all = this->repo->listMedication();
    assert(all[0].getId() == 1);
    assert(all[0].getName() == "a");
    assert(all[0].getConcentration() == 20);
    assert(all[0].getQuantity() == 20);
}

void TestRepository::runTests(){
    this->testAddNewMedication();
    this->testDeleteMedication();
    this->testUpdateMedication();
}
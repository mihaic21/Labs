//
//  Controller.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Controller.h"

Controller::Controller(RepositoryAbstract* repo){
    this->repo = repo;
}

Controller::~Controller(){
    delete this->repo;
}


vector<int> Controller::addNewMedication(string name, int concentration, int quantity){
    Medication *med = new Medication(0, name, concentration, quantity);
    
    MedicationValidator *validator = new MedicationValidator();
    vector<int> errors = validator->validate(*med);
    
    if (errors.size() == 0) this->repo->addNewMedication(*med);
    
    delete med;
    delete validator;
    return errors;
}

vector<Medication> Controller::listMedication(){
    return this->repo->listMedication();
}

int Controller::deleteMedication(int id){
    return this->repo->deleteMedication(id);
}

vector<int> Controller::updateMedication(int id, string name, int concentration, int quantity){
    Medication *med = new Medication(id, name, concentration, quantity);
    
    MedicationValidator *validator = new MedicationValidator();
    vector<int> errors = validator->validate(*med);
    
    if (errors.size() == 0) this->repo->updateMedication(id, name, concentration, quantity);
    
    delete med;
    delete validator;
    return errors;

}

vector<int> Controller::addNewSale(int medicationId, int date, int quantity){
    Medication *med = this->repo->getMedicationForId(medicationId);
    vector<int> errors;
    if (med || medicationId == -1){
        Sale *sale;
        if (medicationId == -1){
            sale = new Sale(NULL, date, quantity);
        } else {
            sale = new Sale(med, date, quantity);
        }
         //= validator->validate(*med);
        if (errors.size() == 0) this->repo->addNewSale(*sale);
        delete sale;
    } else {
        errors.push_back(REPOSITORY_ID_NOT_FOUND);
    }
    return errors;
}

vector<Sale> Controller::listSales(){
    return this->repo->listSales();
}

int Controller::deleteSale(int id){
    return this->repo->deleteSale(id);
}

vector<int> Controller::updateSale(int id, int date, int quantity){
    Sale *sale = new Sale(NULL, date, quantity);
    
    //validator
    vector<int> errors;// = validator->validate(*med);
    
    if (errors.size() == 0) this->repo->updateSale(id, date, quantity);
    
    delete sale;
    //delete validator;
    return errors;
    
}


vector<Medication> Controller::sort(const int criteria, const int direction){
    return this->repo->sort(criteria, direction);
}

vector<Medication> Controller::filter(const int criteria, const int value){
    return this->repo->filter(criteria, value);
}

bool Controller::medicationIdFound(int id){
    return this->repo->medicationIdFound(id);
}

bool Controller::saleIdFound(int id){
    return this->repo->saleIdFound(id);
}

Medication* Controller::getMedicationForId(int id){
    return this->repo->getMedicationForId(id);
}

Sale* Controller::getSaleForId(int id){
    return this->repo->getSaleForId(id);
}

vector<Sale> Controller::getTopFive(int startDate, int endDate){
    return this->repo->getTopFive(startDate, endDate);
}
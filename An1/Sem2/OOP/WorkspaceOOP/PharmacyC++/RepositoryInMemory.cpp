//
//  RepositoryInMemory.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 12/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "RepositoryInMemory.h"

#include <algorithm>
#include <sstream>

#pragma mark - Constructor, Initialization

RepositoryInMemory::RepositoryInMemory(){
    this->lastMedicationId = 0;
    this->lastSaleId = 0;
}

#pragma mark - General Template Methods

template <class T>
bool RepositoryInMemory::idFound(vector<T> &list, int id){
    for (int i=0; i<list.size(); i++){
        if (list[i].getId() == id){
            return true;
        }
    }
    return false;
}

template <class T>
int RepositoryInMemory::positionForId(vector<T>& list, int id){
    for (int i=0; i<list.size(); i++){
        if (list[i].getId() == id){
            return i;
        }
    }
    return -1;
}

template <class T>
void RepositoryInMemory::addNewObject(vector<T>& list, T object) {
    list.push_back(object);
}

template <class T>
int RepositoryInMemory::deleteObject(vector<T>& list, int id){
    if (this->idFound<T>(list, id)){
        int pos = this->positionForId<T>(list, id);
        list.erase(list.begin()+pos);
        return 0;
    }
    return -1;
}

template <class T>
T* RepositoryInMemory::getObjectForId(vector<T> &list, int id){
    for (int i=0; i<list.size(); i++){
        if (list[i].getId() == id){
            return &list[i];
        }
    }
    return NULL;
}

template <class T>
vector<T> RepositoryInMemory::getObjectList(vector<T> &list) {
    return list;
}

void RepositoryInMemory::updateMedication(int id, string name, int concentration, int quantity){
    int pos = this->medicationPositionForId(id);
    if (pos != -1){
        if (name == ""){
            name = this->medicationList[pos].getName();
        }
        if (concentration == 0){
            name = this->medicationList[pos].getConcentration();
        }
        if (quantity == 0){
            quantity = this->medicationList[pos].getQuantity();
        }
        this->medicationList[pos].setName(name);
        this->medicationList[pos].setConcentration(concentration);
        this->medicationList[pos].setQuantity(quantity);
    }
}

void RepositoryInMemory::updateSale(int id, int date, int quantity){
    int pos = this->salePositionForId(id);
    if (pos != -1){
        if (date == 0){
            date = this->saleList[pos].getDate();
        }
        if (quantity == 0){
            quantity = this->saleList[pos].getQuantity();
        }
        this->saleList[pos].setDate(date);
        this->saleList[pos].setQuantity(quantity);
    }
}


#pragma mark - Sorting and Filtering

/*
 * Helper method that compares medication based on some criteria
 */

template <class T>
bool compareMedication(Medication& m1, Medication& m2) {
    if (typeid(T) == typeid(int)){
        return (m1.getQuantity() < m2.getQuantity());
    }
    if (typeid(T) == typeid(string)){
        return (m1.getName() < m2.getName());
    }
    return false;
}

vector<Medication> RepositoryInMemory::sort(const int criteria, const int direction) {
    vector<Medication> result = this->medicationList;
    switch (criteria){
        case SORT_BY_NAME:
            std::sort(result.begin(), result.end(), compareMedication<string>);
            break;
            
        case SORT_BY_QUANTITY:
            std::sort(result.begin(), result.end(), compareMedication<int>);
            break;
            
        default:
            break;
    }
    if (direction == SORT_DIRECTION_DSC){
        reverse(result.begin(), result.end());
    }
    return result;
    
}

vector<Medication> RepositoryInMemory::filter(const int criteria, const int value){
    vector<Medication> result = this->medicationList;
    switch (criteria) {
        case FILTER_BY_QUANTITY_LESS_THAN:
            for (int i=0; i<result.size(); i++){
                if (result[i].getQuantity() > value){
                    result.erase(result.begin()+i);
                    i--;
                }
            }
            break;
            
        default:
            break;
    }
    return result;
}

#pragma mark - Convenience Methods

bool RepositoryInMemory::medicationIdFound(int id){
    return this->idFound<Medication>(this->medicationList, id);
}

bool RepositoryInMemory::saleIdFound(int id){
    return this->idFound<Sale>(this->saleList, id);
}

int RepositoryInMemory::medicationPositionForId(int id){
    return this->positionForId<Medication>(this->medicationList, id);
}

int RepositoryInMemory::salePositionForId(int id){
    return this->positionForId<Sale>(this->saleList, id);
}

Medication* RepositoryInMemory::getMedicationForId(int id){
    return this->getObjectForId<Medication>(this->medicationList, id);
}

Sale* RepositoryInMemory::getSaleForId(int id){
    return this->getObjectForId(this->saleList, id);
}

void RepositoryInMemory::addNewMedication(Medication med){
    if (this->lastMedicationId == 0 && !medicationIdFound(0)) {
        med.setId(0);
    } else {
        med.setId(++this->lastMedicationId);
    }
    this->addNewObject<Medication>(this->medicationList, med);
}

vector<Medication> RepositoryInMemory::listMedication(){
    return this->getObjectList<Medication>(this->medicationList);
}

int RepositoryInMemory::deleteMedication(int id){
    return this->deleteObject<Medication>(this->medicationList, id);
}

void RepositoryInMemory::addNewSale(Sale sale){
    if (this->lastSaleId == 0 && !saleIdFound(0)){
        sale.setId(0);
    } else {
        sale.setId(++this->lastSaleId);
    }
    this->addNewObject<Sale>(this->saleList, sale);
}

vector<Sale> RepositoryInMemory::listSales(){
    return this->getObjectList<Sale>(this->saleList);
}

int RepositoryInMemory::deleteSale(int id){
    return this->deleteObject<Sale>(this->saleList, id);
}

bool compareSaleByQuantity(Sale& s1, Sale& s2){
    return s1.getQuantity() < s2.getQuantity();
}

vector<Sale> RepositoryInMemory::getTopFive(int startDate, int endDate){
    vector<Sale> top;
    for (int i=0; i<this->saleList.size(); i++){
        bool found = false;
        for (int j=0; j<top.size(); j++){
            if (this->saleList[i].getMedication() == top[j].getMedication() && this->saleList[i].getDate()>=startDate && this->saleList[i].getDate()<=endDate){
                found = true;
                top[j].setQuantity(top[j].getQuantity()+this->saleList[i].getQuantity());
                break;
            }
        }
        
        if (!found && this->saleList[i].getDate()>=startDate && this->saleList[i].getDate()<=endDate){
            Sale *sale = new Sale(this->saleList[i].getMedication(), this->saleList[i].getDate(), this->saleList[i].getQuantity());
            top.push_back(*sale);
            delete sale;
        }
    }
    
    if (top.size() < 5){
        return top;
    }
    
    vector<Sale> topFive;
    std::sort(top.begin(), top.end(), compareSaleByQuantity);
    
    for (int i=0; i<top.size(); i++){
        topFive.push_back(top[i]);
    }
    
    return topFive;
}


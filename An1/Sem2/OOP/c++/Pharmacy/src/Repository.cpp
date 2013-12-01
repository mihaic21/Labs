//
//  Repository.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <fstream>
#include <algorithm>
#include <sstream>

#include "Repository.h"

#pragma mark - Constructor, Initialization and IO Management

Repository::Repository(string filename){
    this->filename = filename;
    this->lastMedicationId = 0;
    this->lastSaleId = 0;
    this->readFromFile();
}

vector<string> split(const string& s, char separator){
    vector<string> tokens;
    stringstream ss(s);
    string item;
    while (getline(ss, item, separator)){
        tokens.push_back(item);
    }
    return tokens;
}

void Repository::readFromFile(){
    fstream file;
    file.open(this->filename, ios::in);
    if (file.is_open()){
        int category = 0;
        while (file.good()){
            string line;
            getline(file, line);
            if (line == REPOSITORY_CATEGORY_SEPARATOR) category++; // If we reach the category separator then we move on to the next category
            else {
                if (line != ""){
                    int id, concentration, quantity, date, medicationId;
                    vector<string> tokens = split(line, REPOSITORY_TOKEN_SEPARATOR);
                    switch (category) {
                        case 0:{ //Medication category
                            string name;
                            id = atoi(tokens[0].c_str());
                            name = tokens[1];
                            concentration = atoi(tokens[2].c_str());
                            quantity = atoi(tokens[3].c_str());
                            Medication *med = new Medication(id, name, concentration, quantity);
                            this->medicationList.push_back(*med);
                            this->lastMedicationId = id;
                            delete med;
                            break;
                        }
                        case 1:{ //Sale category
                            id = atoi(tokens[0].c_str());
                            date = atoi(tokens[1].c_str());
                            quantity = atoi(tokens[2].c_str());
                            medicationId = atoi(tokens[3].c_str());
                            Sale *sale = new Sale(this->getMedicationForId(medicationId), date, quantity);
                            sale->setId(id);
                            this->saleList.push_back(*sale);
                            this->lastSaleId = id;
                            delete sale;
                            break;
                        }
                            
                    }
                }
            }
        }
    file.close();
    }
}

void Repository::writeToFile(){
    fstream file;
    file.open(this->filename, ios::out);
    if (file.is_open()){
        
        // Medication
        
        for (int i=0; i<(int)this->medicationList.size(); i++){
            file << this->medicationList[i].getId() << REPOSITORY_TOKEN_SEPARATOR <<
            this->medicationList[i].getName() << REPOSITORY_TOKEN_SEPARATOR <<
            this->medicationList[i].getConcentration() << REPOSITORY_TOKEN_SEPARATOR <<
            this->medicationList[i].getQuantity() << endl;
        }
        
        // Sale
        
        file << REPOSITORY_CATEGORY_SEPARATOR << endl;
        for (int i=0; i<this->saleList.size(); i++){
            file << this->saleList[i].getId() << REPOSITORY_TOKEN_SEPARATOR <<
            this->saleList[i].getDate() << REPOSITORY_TOKEN_SEPARATOR <<
            this->saleList[i].getQuantity() << REPOSITORY_TOKEN_SEPARATOR <<
            this->saleList[i].getMedication()->getId() << endl;
        }
        file.close();
    }
}

#pragma mark - General Template Methods

template <class T>
bool Repository::idFound(vector<T> &list, int id){
    for (int i=0; i<list.size(); i++){
        if (list[i].getId() == id){
            return true;
        }
    }
    return false;
}

template <class T>
int Repository::positionForId(vector<T>& list, int id){
    for (int i=0; i<list.size(); i++){
        if (list[i].getId() == id){
            return i;
        }
    }
    return -1;
}

template <class T>
void Repository::addNewObject(vector<T>& list, T object) {
    list.push_back(object);
    this->writeToFile();
}

template <class T>
int Repository::deleteObject(vector<T>& list, int id){
    if (this->idFound<T>(list, id)){
        int pos = this->positionForId<T>(list, id);
        list.erase(list.begin()+pos);
        this->writeToFile();
        return 0;
    }
    return -1;
}

template <class T>
T* Repository::getObjectForId(vector<T> &list, int id){
    for (int i=0; i<list.size(); i++){
        if (list[i].getId() == id){
            return &list[i];
        }
    }
    return NULL;
}

template <class T>
vector<T> Repository::getObjectList(vector<T> &list) {
    return list;
}

void Repository::updateMedication(int id, string name, int concentration, int quantity){
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
        this->writeToFile();
    }
}

void Repository::updateSale(int id, int date, int quantity){
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
        this->writeToFile();
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

vector<Medication> Repository::sort(const int criteria, const int direction) {
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

vector<Medication> Repository::filter(const int criteria, const int value){
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

bool Repository::medicationIdFound(int id){
    return this->idFound<Medication>(this->medicationList, id);
}

bool Repository::saleIdFound(int id){
    return this->idFound<Sale>(this->saleList, id);
}

int Repository::medicationPositionForId(int id){
    return this->positionForId<Medication>(this->medicationList, id);
}

int Repository::salePositionForId(int id){
    return this->positionForId<Sale>(this->saleList, id);
}
                                              
Medication* Repository::getMedicationForId(int id){
    return this->getObjectForId<Medication>(this->medicationList, id);
}

Sale* Repository::getSaleForId(int id){
    return this->getObjectForId(this->saleList, id);
}

void Repository::addNewMedication(Medication med){
    if (this->lastMedicationId == 0 && !medicationIdFound(0)) {
        med.setId(0);
    } else {
        med.setId(++this->lastMedicationId);
    }
    this->addNewObject<Medication>(this->medicationList, med);
}

vector<Medication> Repository::listMedication(){
    return this->getObjectList<Medication>(this->medicationList);
}

int Repository::deleteMedication(int id){
    return this->deleteObject<Medication>(this->medicationList, id);
}

void Repository::addNewSale(Sale sale){
    if (this->lastSaleId == 0 && !saleIdFound(0)){
        sale.setId(0);
    } else {
        sale.setId(++this->lastSaleId);
    }
    this->addNewObject<Sale>(this->saleList, sale);
}

vector<Sale> Repository::listSales(){
    return this->getObjectList<Sale>(this->saleList);
}

int Repository::deleteSale(int id){
    return this->deleteObject<Sale>(this->saleList, id);
}

bool compareSaleByQuantity(Sale& s1, Sale& s2){
    return s1.getQuantity() < s2.getQuantity();
}

vector<Sale> Repository::getTopFive(int startDate, int endDate){
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

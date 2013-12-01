//
//  Console.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>

#include "Console.h"
using namespace std;

#define GREETING "Pharmacy Management\n"
#define MENU "1. Add Medication\n" \
             "2. Medication List\n" \
             "3. Delete Medication\n" \
             "4. Update Medication\n" \
             "5. Add Sale\n" \
             "6. Sale List\n" \
             "7. Delete Sale\n" \
             "8. Update Sale\n" \
             "9. Sort Medication by Name:\n" \
             "      1 - Ascending\n" \
             "      2 - Descending\n" \
             "10. Sort Medication by Quantity:\n" \
             "      1 - Ascending\n" \
             "      2 - Descending\n" \
             "11. List Medication With Quantity Less Than:\n"\
             "0. Exit\n";

#define SEPARATOR "----------------------------------------------\n"
#define EXIT_CHAR 0

//Constructor
Console::Console(Controller* controller){
    this->controller = controller;
}

//Destructor
Console::~Console(){
    delete this->controller;
}

//Helper function that prints out a vector of objects
template <class T>
void printList(vector<T> list){
    for (int i=0; i<list.size(); i++){
        cout << list[i];
    }
}



//Helper function that prints out a vector of errors
void printErrors(vector<int> errors){
    for (int i=0; i<errors.size(); i++){
        switch (errors[i]) {
            case REPOSITORY_ID_NOT_FOUND:
                cout << "Id not found!" << endl;
                break;
                
            case VALIDATOR_EMPTY_NAME:
                cout << "Name can not be empty!" << endl;
                break;
                
            case VALIDATOR_NEGATIVE_CONCENTRATION:
            case VALIDATOR_ZERO_CONCENTRATION:
                cout << "Concentration must be a non-zero positive int!" << endl;
                break;
                
            case VALIDATOR_NEGATIVE_QUANTITY:
            case VALIDATOR_ZERO_QUANTITY:
                cout << "Quantity must be a non-zero positive int!" << endl;
                break;
        }
    }
}

void Console::addNewMedication(){
    int concentration, quantity;
    string name;
    cout << "Name:";
    cin >> name;
    cout << "Concentration:";
    cin >> concentration;
    cout << "Quantity:";
    cin >> quantity;
    vector<int> errors = this->controller->addNewMedication(name, concentration, quantity);
    if (errors.size() != 0){
        printErrors(errors);
    } else {
        cout << "Medication was added successfully!" << endl;
    }
}

void Console::listMedication(){
    vector<Medication> result = this->controller->listMedication();
    printList<Medication>(result);
}

void Console::deleteMedication(){
    int id;
    vector<int> errors;
    cout << "Id:";
    cin >> id;
    if (!this->controller->medicationIdFound(id)){
        errors.push_back(REPOSITORY_ID_NOT_FOUND);
        printErrors(errors);
        return;
    }
    this->controller->deleteMedication(id);
    cout << "Medication deleted successfully";
}

void Console::updateMedication(){
    int id;
    vector<int> errors;
    cout << "Id:";
    cin >> id;
    if (!this->controller->medicationIdFound(id)){
        errors.push_back(REPOSITORY_ID_NOT_FOUND);
        printErrors(errors);
        return;
    }
    string name = "";
    int concentration = 0, quantity = 0;
    cout << "New name:";
    cin >> name;
    cout << "New concentration:";
    cin >> concentration;
    cout << "New quantity:";
    cin >> quantity;
    
    errors = this->controller->updateMedication(id, name, concentration, quantity);
    if (errors.size() != 0){
        printErrors(errors);
    } else {
        cout << "Medication updated successfully!" << endl;
    }
    
}

void Console::addNewSale(){
    int medicationId, date, quantity;
    cout << "Medication Id:";
    cin >> medicationId;
    cout << "Date:";
    cin >> date;
    cout << "Quantity:";
    cin >> quantity;
    vector<int> errors = this->controller->addNewSale(medicationId, date, quantity);
    if (errors.size() != 0){
        printErrors(errors);
    } else {
        cout << "Sale was added successfully!" << endl;
    }
}

void Console::listSales(){
    vector<Sale> result = this->controller->listSales();
    printList<Sale>(result);
}

void Console::deleteSale(){
    int id;
    vector<int> errors;
    cout << "Id:";
    cin >> id;
    if (!this->controller->saleIdFound(id)){
        errors.push_back(REPOSITORY_ID_NOT_FOUND);
        printErrors(errors);
        return;
    }
    this->controller->deleteSale(id);
    cout << "Sale deleted successfully" << endl;
}

void Console::updateSale(){
    int id;
    vector<int> errors;
    cout << "Id:";
    cin >> id;
    if (!this->controller->saleIdFound(id)){
        errors.push_back(REPOSITORY_ID_NOT_FOUND);
        printErrors(errors);
        return;
    }
    int date = 0, quantity = 0;
    cout << "New Date:";
    cin >> date;
    cout << "New Quantity:";
    cin >> quantity;
    
    errors = this->controller->updateSale(id, date, quantity);
    if (errors.size() != 0){
        printErrors(errors);
    } else {
        cout << "Sale updated successfully!" << endl;
    }
    
}


void Console::sortByName(){
    char input;
    cout << "   >";
    cin >> input;
    vector<Medication> result;
    switch (input) {
        case '1':
            result = this->controller->sort(SORT_BY_NAME, SORT_DIRECTION_ASC);
            break;
        case '2':
            result = this->controller->sort(SORT_BY_NAME, SORT_DIRECTION_DSC);
            break;
            
        default:
            cout << "INVALID CHOICE!";
            break;
    }
    printList<Medication>(result);
    
}

void Console::sortByQuantity(){
    char input;
    cout << "   >";
    cin >> input;
    vector<Medication> result;
    switch (input) {
        case '1':
            result = this->controller->sort(SORT_BY_QUANTITY, SORT_DIRECTION_ASC);
            break;
        case '2':
            result = this->controller->sort(SORT_BY_QUANTITY, SORT_DIRECTION_DSC);
            break;
            
        default:
            cout << "INVALID CHOICE!" << endl;
            break;
    }
    printList<Medication>(result);
}

void Console::filterByQuantity(){
    int quantity;
    cout << "Quantity:";
    cin >> quantity;
    vector<Medication> result;
    result = this->controller->filter(FILTER_BY_QUANTITY_LESS_THAN, quantity);
    printList<Medication>(result);
}

void Console::run(){
    cout << GREETING;
    int user_input = -1;
    while (user_input != EXIT_CHAR){
        cout << SEPARATOR;
        cout << MENU;
        cout << SEPARATOR;
        cin >> user_input;
        switch (user_input) {
            case 0:
                return;
                break;
            
            case 1:
                addNewMedication();
                break;
                
            case 2:
                listMedication();
                break;
            
            case 3:
                deleteMedication();
                break;
            
            case 4:
                updateMedication();
                break;

            case 5:
                addNewSale();
                break;
                
            case 6:
                listSales();
                break;
            
            case 7:
                deleteSale();
                break;
                
            case 8:
                updateSale();
   
            case 9:
                sortByName();
                break;
   
            case 10:
                sortByQuantity();
                break;
                
            case 11:
                filterByQuantity();
                break;
                
            default:
                cout << "INVALID CHOICE" << endl;
                break;
        }
    }
    return;
    
}
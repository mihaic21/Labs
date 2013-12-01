//
//  Controller.h
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Controller__
#define __PharmacyC____Controller__

#include <iostream>
#include <vector>

#include "Repository.h"
#include "RepositoryInMemory.h"
#include "Medication.h"
#include "Validator.h"

class Controller{
private:
    /*
     * Private property that holds a pointer to the repository
     */
    RepositoryAbstract *repo;
    
public:
    /*
     * Constructor
     */
    Controller(RepositoryAbstract*);
    
    /*
     * Destructor
     */
    ~Controller();
    
    
    
    /*
     * Controller method for adding new items to the repository
     */
    vector<int> addNewMedication(string name, int concentration, int quantity);
    
    /*
     * Controller method that gets the entire medication list from the repository
     */
    vector<Medication> listMedication();
    
    /*
     * Controller method for deleting items from the repository
     */
    int deleteMedication(int id);
    
    /*
     * Controller method for updating items in the repository
     */
    vector<int> updateMedication(int id, string name, int concentration, int quantity);
    
    /*
     * Controller method for sorting items in the repository
     */
    vector<Medication> sort(const int criteria, const int direction);
    
    /*
     * Controller method for filtering items in the repository
     */
    vector<Medication> filter(const int criteria, const int value);
    
    /*
     * Controller method for checking if an id is present in the repository
     */
    bool medicationIdFound(int id);
    
    bool saleIdFound(int id);
    
    vector<int> addNewSale(int medicationId, int date, int quantity);
    vector<Sale> listSales();
    int deleteSale(int id);
    vector<int> updateSale(int id, int date, int quantity);
    vector<Sale> getTopFive(int startDate, int endDate);
    
    Medication* getMedicationForId(int id);
    Sale* getSaleForId(int id);
};

#endif /* defined(__PharmacyC____Controller__) */

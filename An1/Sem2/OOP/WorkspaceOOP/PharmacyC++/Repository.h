//
//  Repository.h
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Repository__
#define __PharmacyC____Repository__

#include <iostream>
#include <string>
#include <vector>

#include "RepositoryAbstract.h"
#include "Medication.h"
#include "Constants.h"
#include "Sale.h"

using namespace std;



class Repository:public RepositoryAbstract{
private:
    /*
     * Repository file name
     */
    string filename;
    
    /*
     * Private property holding the id of the last product
     */
    int lastMedicationId;
    
    /*
     * Private property holding the id of the last sale
     */
    int lastSaleId;
    
    /*
     * The vector holding the entire medication list
     */
    vector<Medication> medicationList;
    
    /*
     * The vector holding the entire sales list
     */
    vector<Sale> saleList;
    
    
    /*
     * Private method that reads from the repository file and loads the medication list
     */
    void readFromFile();
    
    /*
     * Private method that writes the medication list to the repository file
     */
    void writeToFile();
    
    /*
     * Private method that returns the position of a medication item in the medication list
     */
    int medicationPositionForId(int id);
    
    int salePositionForId(int id);
    
    template <class T>
    void addNewObject(vector<T>& list, T object);
    
    template <class T>
    int deleteObject(vector<T>& list, int id);
    
    template <class T>
    bool idFound(vector<T>& list, int id);
    
    template <class T>
    int positionForId(vector<T>& list, int id);
    
    template <class T>
    T* getObjectForId(vector<T>& list, int id);
    
    template <class T>
    vector<T> getObjectList(vector<T>& list);
    
    
public:
    /*
     * Constructor
     * Initialises the repository and calls the readFromFile() method
     */
    Repository(string filename);
    
    
    /*
     * Public method that adds a new item in the repository
     */
    void addNewMedication(Medication);
    
    /*
     * Public method that returns the medication list
     */
    vector<Medication> listMedication();
    
    /*
     * Public method to delete an item from the medication list
     * Returns 0 if succeded any other int if an error occoured
     */
    int deleteMedication(int id);
    
    /*
     * Public method that updates an item in the medication list with the details passed
     */
    void updateMedication(int id, string name, int concentration, int quantity);
    
    /*
     * Public method that returns a sorted list based on the criteria and direction
     */
    
    void addNewSale(Sale);
    vector<Sale> listSales();
    int deleteSale(int id);
    void updateSale(int id, int date, int quantity);
    
    vector<Medication> sort(const int criteria, const int direction);
    
    /*
     * Public method that returns a filtered list using the criteria and value passed
     */
    vector<Medication> filter(const int criteria, const int value);
    
    /*
     * Public method that checks if an id is present in the medication list
     */
    bool medicationIdFound(int id);
    
    
    bool saleIdFound(int id);
    
    /*
     * Public method that returns a pointer to a medication in the medication list, based on the passed id
     */
    Medication* getMedicationForId(int id);
    
    /*
     * Public method that returns a pointer to a sale in the sale list, based on the passed it
     */
    Sale* getSaleForId(int id);
    
    vector<Sale> getTopFive(int startDate, int endDate);
};

#endif /* defined(__PharmacyC____Repository__) */

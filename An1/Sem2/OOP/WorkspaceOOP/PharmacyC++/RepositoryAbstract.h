//
//  RepositoryAbstract.h
//  PharmacyC++
//
//  Created by Mihai Costea on 12/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____RepositoryAbstract__
#define __PharmacyC____RepositoryAbstract__

#include <iostream>
#include <vector>

#include "Medication.h"
#include "Sale.h"

#define REPOSITORY_ID_NOT_FOUND -1
#define REPOSITORY_CATEGORY_SEPARATOR "###"
#define REPOSITORY_TOKEN_SEPARATOR '|'

class RepositoryAbstract {
    
public:
    RepositoryAbstract(){};
    
    virtual ~RepositoryAbstract(){};
    
    virtual void addNewMedication(Medication)=0;
    virtual vector<Medication> listMedication()=0;
    virtual int deleteMedication(int id)=0;
    virtual void updateMedication(int id, string name, int concentration, int quantity)=0;
    
    virtual void addNewSale(Sale)=0;
    virtual vector<Sale> listSales()=0;
    virtual int deleteSale(int id)=0;
    virtual void updateSale(int id, int date, int quantity)=0;
    
    virtual vector<Medication> sort(const int criteria, const int direction)=0;
    virtual vector<Medication> filter(const int criteria, const int value)=0;
    
    virtual bool medicationIdFound(int id)=0;
    virtual bool saleIdFound(int id)=0;
    
    virtual Medication* getMedicationForId(int id)=0;
    virtual Sale* getSaleForId(int id)=0;
    
    virtual vector<Sale> getTopFive(int startDate, int endDate)=0;

};


#endif /* defined(__PharmacyC____RepositoryAbstract__) */

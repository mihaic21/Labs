//
//  Sale.h
//  PharmacyC++
//
//  Created by Mihai Costea on 10/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Sale__
#define __PharmacyC____Sale__

#include <iostream>

#include "Medication.h"

class Sale{
private:
    Medication* medication;
    int id;
    int date;
    int quantity;
public:
    Sale();
    Sale(Medication* medication, int date, int quantity);
    int getId();
    void setId(int id);
    int getDate();
    void setDate(int date);
    int getQuantity();
    void setQuantity(int quantity);
    Medication* getMedication();
    friend ostream& operator<< (ostream& os, Sale& sale);
};

#endif /* defined(__PharmacyC____Sale__) */

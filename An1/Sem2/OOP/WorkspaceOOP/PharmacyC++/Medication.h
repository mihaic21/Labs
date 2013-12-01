//
//  Medication.h
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Medication__
#define __PharmacyC____Medication__

#include <iostream>
#include <string>

#include "Substance.h"
using namespace std;

class Medication:public Substance{
private:
    int id;
    int quantity;
public:
    Medication();
    Medication(int id, string name, int concentration, int quantity);
    int getId();
    void setId(int);
    int getQuantity();
    void setQuantity(int);
    friend ostream& operator<<(ostream& os, Medication& med);
};

#endif /* defined(__PharmacyC____Medication__) */

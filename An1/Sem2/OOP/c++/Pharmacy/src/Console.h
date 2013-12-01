//
//  Console.h
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Console__
#define __PharmacyC____Console__

#include <iostream>
#include "Controller.h"
#include "Constants.h"
#include "Medication.h"


class Console{
private:
    /*
     * Private property that holds a pointer to the controller
     */
    Controller *controller;
    void addNewMedication();
    void listMedication();
    void deleteMedication();
    void updateMedication();
    void addNewSale();
    void listSales();
    void deleteSale();
    void updateSale();
    void sortByName();
    void sortByQuantity();
    void filterByQuantity();

public:
    Console(Controller *controller);
    ~Console();
    void run();
};

#endif /* defined(__PharmacyC____Console__) */

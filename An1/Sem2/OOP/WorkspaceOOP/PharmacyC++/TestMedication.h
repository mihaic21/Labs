//
//  TestMedication.h
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____TestMedication__
#define __PharmacyC____TestMedication__

#include <iostream>

#include "Medication.h"

class TestMedication{
public:
    TestMedication();

private:
    void runTests();
    void testId();
    void testName();
    void testQuantity();
    void testConcentration();
};

#endif /* defined(__PharmacyC____TestMedication__) */

//
//  AllTests.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "AllTests.h"

#include "TestRepository.h"
#include "TestMedication.h"


void runAllTests(){
    TestMedication *tm = new TestMedication();
    TestRepository *tr = new TestRepository();
    
    delete tm;
    delete tr;
}
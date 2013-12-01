//
//  TestRepository.h
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____TestRepository__
#define __PharmacyC____TestRepository__

#include <iostream>
#include "Repository.h"
#include "Medication.h"


class TestRepository{
private:
    Repository *repo;
    void runTests();
    void testAddNewMedication();
    void testDeleteMedication();
    void testUpdateMedication();
    
public:
    TestRepository();
};

#endif /* defined(__PharmacyC____TestRepository__) */

//
//  Validator.h
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Validator__
#define __PharmacyC____Validator__

#include <iostream>
#include <vector>

#include "Medication.h"
#include "PharmacyException.h"


class MedicationValidator{
private:
    Medication medication;
    vector<int> errors;
    void validateName() throw(PharmacyException);
    void validateConcentration() throw(PharmacyException);
    void validateQuantity() throw(PharmacyException);
public:
    MedicationValidator();
    vector<int> validate(const Medication&);
};

#endif /* defined(__PharmacyC____Validator__) */

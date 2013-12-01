//
//  Validator.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 31/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <string>

#include "Validator.h"


MedicationValidator::MedicationValidator(){
    
}

void MedicationValidator::validateName() throw(PharmacyException){
    if (this->medication.getName() == "") throw PharmacyException(VALIDATOR_EMPTY_NAME);
}

void MedicationValidator::validateConcentration() throw(PharmacyException){
    if (this->medication.getConcentration() == 0) throw PharmacyException(VALIDATOR_ZERO_CONCENTRATION);
    if (this->medication.getConcentration() < 0) throw PharmacyException(VALIDATOR_NEGATIVE_CONCENTRATION);
}

void MedicationValidator::validateQuantity() throw(PharmacyException){
    if (this->medication.getConcentration() == 0) throw PharmacyException(VALIDATOR_ZERO_QUANTITY);
    if (this->medication.getConcentration() < 0) throw PharmacyException(VALIDATOR_NEGATIVE_QUANTITY);
}

vector<int> MedicationValidator::validate(const Medication &med){
    this->medication = med;
    try {
        this->validateName();
    } catch (PharmacyException pe) {
        this->errors.push_back(pe.getError());
    }
    
    try {
        this->validateConcentration();
    } catch (PharmacyException pe) {
        this->errors.push_back(pe.getError());
    }
    
    try {
        this->validateQuantity();
    } catch (PharmacyException pe) {
        this->errors.push_back(pe.getError());
    }
    
    return this->errors;
}
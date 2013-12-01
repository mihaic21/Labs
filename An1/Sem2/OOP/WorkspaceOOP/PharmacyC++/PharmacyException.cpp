//
//  PharmacyException.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 20/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "PharmacyException.h"


PharmacyException::PharmacyException(int error){
    this->_error = error;
}

int PharmacyException::getError(){
    return this->_error;
}
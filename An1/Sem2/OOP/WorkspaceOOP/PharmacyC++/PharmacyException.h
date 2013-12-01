//
//  PharmacyException.h
//  PharmacyC++
//
//  Created by Mihai Costea on 20/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____PharmacyException__
#define __PharmacyC____PharmacyException__

#include <iostream>
#include <exception>

#define VALIDATOR_VALID 0
#define VALIDATOR_EMPTY_NAME 1
#define VALIDATOR_ZERO_CONCENTRATION 2
#define VALIDATOR_NEGATIVE_CONCENTRATION 3
#define VALIDATOR_ZERO_QUANTITY 4
#define VALIDATOR_NEGATIVE_QUANTITY 5

using namespace std;

class PharmacyException:public exception {
private:
    int _error;
    
public:
    PharmacyException(int error);
    int getError();

    
};

#endif /* defined(__PharmacyC____PharmacyException__) */

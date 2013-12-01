//
//  Substance.h
//  PharmacyC++
//
//  Created by Mihai Costea on 10/4/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __PharmacyC____Substance__
#define __PharmacyC____Substance__

#include <iostream>
#include <string>

using namespace std;


class Substance {
private:
    string name;
    int concentration;
    
public:
    Substance();
    Substance(string name, int concentration);
    string getName();
    void setName(string name);
    int getConcentration();
    void setConcentration(int concentration);

};

#endif /* defined(__PharmacyC____Substance__) */

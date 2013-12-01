//
//  main.cpp
//  PharmacyC++
//
//  Created by Mihai Costea on 29/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <iostream>
#include "Console.h"
#include "Repository.h"
#include "RepositoryInMemory.h"
#include "Controller.h"
#include "AllTests.h"


int main(int argc, const char * argv[])
{
    runAllTests();
    RepositoryAbstract *repository = new Repository("pharmacy.txt");
    //RepositoryAbstract *repository = new RepositoryInMemory();
    Controller *controller = new Controller(repository);
    Console *console = new Console(controller);
    
    
    console->run();
    
    delete console;
    
    return 0;
}


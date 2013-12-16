package com.company;

/*
Requirement:
    On a table there are cups filled with tea, coffee and juice.
    Print in a text file all the beverages than can be decorated with whipped cream.
    The beverages are read from another text file.
 */


import Controller.Controller;
import Model.Beverage;
import Repository.Repository;

public class Main {

    public static void main(String[] args) {

        Repository<Beverage> repo = new Repository<Beverage>();
        Controller ctrl = new Controller(repo);



    }
}

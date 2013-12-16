package com.company;

/*
    In a backpack there are sandwiches, juice bottles and cans.
    Print in a text file all the products that have expired.
    The products are read from another text file.
 */


import Model.Product;
import Repository.Repository;
import Controller.Controller;


public class Main {

    public static void main(String[] args) {
        Repository<Product> repo = new Repository<Product>();
        Controller ctrl = new Controller(repo);
    }
}

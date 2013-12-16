package com.company;

import Controller.Controller;
import Repository.Repository;

public class Main {

    public static void main(String[] args) {
        Repository repo = new Repository();
        Controller ctrl = new Controller(repo);
    }
}

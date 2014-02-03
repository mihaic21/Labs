package com.company;

import Controller.Controller;
import Model.Goodies;
import Repository.Repository;
import Repository.RepoInterface;
import UI.Console;

public class Main {

    public static void main(String[] args) {

        RepoInterface<Goodies> repo = new Repository<Goodies>();
        Controller ctrl = new Controller(repo);
        Console cons = new Console(ctrl);

    }
}

package com.company;

import Model.Student;
import Repository.Repository;
import Controller.Controller;
import Controller.Validator;
import UI.Console;

public class Main {

    public static void main(String[] args) {
        Repository<Student> repo = new Repository<Student>();
        Validator val = new Validator(repo);
        Controller ctrl = new Controller(repo, val);
        Console cons = new Console(ctrl);
    }
}

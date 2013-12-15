package Main;

import Controller.Controller;
import Controller.Validator;
import Repository.Repository;
import UI.Console;

public class Main {

    public static void main(String[] args) {

        Repository repo = new Repository();
        Validator val = new Validator(repo);
        Controller ctrl = new Controller(repo, val);
        Console cons = new Console(ctrl);

    }
}

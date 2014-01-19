import Controller.*;
import UI.Console;
import Repository.Repository;

public class Main {

    public static void main(String[] args) {

        Repository repo = new Repository();
        Validator val = new Validator(repo);
        Controller ctrl = new Controller(repo, val);
        Console cons = new Console(ctrl);

    }
}
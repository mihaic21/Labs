/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */

import Repository.Repository;
import Controller.*;
import UI.Console;

public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Repository repo = new Repository(100);
        Validator val = new Validator(repo);
        Controller ctrl = new Controller(repo,val);
        Console cons = new Console(ctrl);
        cons.run();
    }
}

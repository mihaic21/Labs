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
import Model.Student;
import UI.Console;

public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Repository<Student> repo = new Repository<Student>();
        Validator val = new Validator(repo);
        Controller ctrl = new Controller(repo,val);
        Console cons = new Console(ctrl);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import lab2.Controller.*;
import lab2.Repository.*;
import lab2.UI.*;

/**
 *
 * @author mihai
 */
public class App {
    
    public static void main(String[] args) {
        Repository repo = new Repository(100);
        Validator val = new Validator(repo);
        Controller ctrl = new Controller(repo,val);
        Console cons = new Console(ctrl);
        cons.run();
    }
}

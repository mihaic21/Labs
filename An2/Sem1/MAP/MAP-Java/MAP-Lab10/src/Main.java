import Controller.Controller;
import Controller.Validator;
import Model.Student;
import Repository.Repository;
import UI.HomeForm;

import javax.swing.*;

/**
 * Created by mihai on 2/1/14.
 */
public class Main {

    public static void main(String[] args){
        Repository<Student> repo = new Repository<Student>();
        Validator val = new Validator(repo);
        final Controller ctrl = new Controller(repo, val);
        ctrl.readFromFile("students.txt");

        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                HomeForm homeForm = new HomeForm(ctrl);
                homeForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });

    }

}

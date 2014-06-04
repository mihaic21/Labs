package UI;

import Controller.*;
import Repository.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mihai on 07.05.2014.
 */
public class HomeForm extends JFrame{

    private JPanel rootPanel;
    private JButton facultyStaffButton;
    private JButton candidatesButton;

    private RepoInterface repository;

    public HomeForm(RepoInterface repository) {

        this.repository = repository;

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    private void initializeListeners(){

        candidatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HomeForm.this.setVisible(false);
                CandidateController controller = new CandidateController(repository);
                CandidatesForm candidatesForm = new CandidatesForm(HomeForm.this, controller);

            }
        });

        facultyStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HomeForm.this.setVisible(false);
                FacultyController controller = new FacultyController(repository);
                FacultyForm facultyForm = new FacultyForm(HomeForm.this, controller);
            }
        });

    }

}

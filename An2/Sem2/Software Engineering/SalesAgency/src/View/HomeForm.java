package View;

import Repository.RepoInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mihai on 22.05.2014.
 */
public class HomeForm extends JFrame{
    private JPanel rootPanel;
    private JButton salesmanButton;
    private JButton adminButton;

    private RepoInterface repository;

    public HomeForm(RepoInterface repository){

        this.repository = repository;

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    private void initializeListeners(){

        this.salesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginForm loginForm = new LoginForm("Salesman",HomeForm.this.repository, HomeForm.this);
                HomeForm.this.setVisible(false);
            }
        });

        this.adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginForm loginForm = new LoginForm("Administrator",HomeForm.this.repository, HomeForm.this);
                HomeForm.this.setVisible(false);
            }
        });

    }

}

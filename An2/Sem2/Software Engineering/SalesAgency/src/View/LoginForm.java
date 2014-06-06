package View;

import Controller.AdminController;
import Controller.LoginController;
import Controller.SalesmanController;
import Repository.RepoInterface;
import Utils.MyException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mihai on 22.05.2014.
 */
public class LoginForm extends JFrame{
    private JPanel rootPanel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JPanel infoPanel;
    private JTextField userTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JPanel buttonsPanel;
    private JButton backButton;


    private String loginType;
    private RepoInterface repository;
    private HomeForm homeForm;

    public LoginForm(String loginType, RepoInterface repository, HomeForm homeForm){

        this.loginType = loginType;
        this.repository = repository;
        this.homeForm = homeForm;

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void initializeListeners(){

        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginForm.this.login();
            }
        });

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginForm.this.dispose();
                LoginForm.this.homeForm.setVisible(true);
            }
        });

    }


    private void login(){

        try{
            if (this.userTextField.getText().length() < 1 || this.passwordTextField.getText().length() < 1){
                throw new MyException("Field cannot be empty");
            }

            if (this.loginType.equals("Administrator")){

                LoginController.validateLogin(this.loginType, this.userTextField.getText(), this.passwordTextField.getText(), this.repository);
                AdminController adminController = new AdminController(this.repository);
                AdminForm adminForm = new AdminForm(this, adminController);

            }else if (this.loginType.equals("Salesman")){

                LoginController.validateLogin(this.loginType, this.userTextField.getText(), this.passwordTextField.getText(), this.repository);
                SalesmanController salesmanController = new SalesmanController(this.repository);
                SalesmanForm salesmanForm = new SalesmanForm(this, salesmanController);

            }
        }catch (MyException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}

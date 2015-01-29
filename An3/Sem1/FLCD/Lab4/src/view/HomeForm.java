package view;

import controller.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Shobo on 15.01.2015.
 */
public class HomeForm extends JFrame{
    private JPanel rootPanel;
    private JTextField stepOneTextField;
    private JButton stepOneButton;
    private JTextField stepTwoTextField;
    private JButton stepTwoButton;

    public HomeForm(){

        initializeListeners();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
    }

    private void buttonOneClicked(){
        Parser parser = new Parser();
        String result = parser.parseStepOne(stepOneTextField.getText());
    }

    private void buttonTwoClicked(){

    }

    private void initializeListeners(){

        stepOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonOneClicked();
            }
        });

        stepTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonTwoClicked();
            }
        });

    }

}

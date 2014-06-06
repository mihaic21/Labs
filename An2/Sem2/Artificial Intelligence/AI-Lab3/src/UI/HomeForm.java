package UI;

import ES.ExpertSystem;
import Fuzz.Fuzzy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mihai on 05.06.2014.
 */
public class HomeForm extends JFrame{
    private JPanel rootPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextArea resultsTextArea;
    private JTextField environmentTextField;
    private JButton forwardButton;
    private JButton backwardButton;
    private JTextField temperatureTextField;
    private JTextField capacityTextField;
    private JButton getResultButton;

    public HomeForm(){

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void initializeListeners(){

        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (environmentTextField.getText().length() != 0){
                    resultsTextArea.setText(new ExpertSystem().solveFacts(environmentTextField.getText(), "Forward"));
                }
            }
        });

        backwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (environmentTextField.getText().length() != 0){
                    resultsTextArea.setText(new ExpertSystem().solveFacts(environmentTextField.getText(), "Backward"));
                }
            }
        });

        getResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (temperatureTextField.getText().length() != 0 && capacityTextField.getText().length() != 0){
                    resultsTextArea.setText(String.valueOf(new Fuzzy().solve(Double.parseDouble(temperatureTextField.getText()), Double.parseDouble(capacityTextField.getText()))));
                }
            }
        });

    }
}

package UI;

import Model.Node;
import SearchMethods.BreadthFirstSearch;
import SearchMethods.DepthFirstSearch;
import SearchMethods.GreedyBestFirstSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by mihai on 29.04.2014.
 */
public class HomeForm extends JFrame {

    private JPanel rootPanel;
    private JComboBox selectionComboBox;
    private JTextArea solutionTextArea;
    private JButton exitButton;

    private String[] comboBoxValues = {"Select Option", "Depth-First Search", "Breadth-First Search", "Greedy Best-First Search"};


    public HomeForm() {

        this.initializeListeners();

        this.populateComboBox();

        this.solutionTextArea.setEditable(false);

        this.setContentPane(rootPanel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void populateComboBox() {

        Vector comboBoxItems = new Vector();
        comboBoxItems.add(this.comboBoxValues[0]);
        comboBoxItems.add(this.comboBoxValues[1]);
        comboBoxItems.add(this.comboBoxValues[2]);
        comboBoxItems.add(this.comboBoxValues[3]);

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboBoxItems);
        selectionComboBox.setModel(comboBoxModel);

    }

    private void initializeListeners() {

        this.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        this.selectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (HomeForm.this.selectionComboBox.getSelectedItem().toString() == HomeForm.this.comboBoxValues[0]) {
                    HomeForm.this.solutionTextArea.setText("");
                } else if (HomeForm.this.selectionComboBox.getSelectedItem().toString() == HomeForm.this.comboBoxValues[1]) {
                    DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
                    Node finalState = depthFirstSearch.solve();
                    HomeForm.this.displaySolution(finalState);
                } else if (HomeForm.this.selectionComboBox.getSelectedItem().toString() == HomeForm.this.comboBoxValues[2]) {
                    BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
                    Node finalState = breadthFirstSearch.solve();
                    HomeForm.this.displaySolution(finalState);
                } else if (HomeForm.this.selectionComboBox.getSelectedItem().toString() == HomeForm.this.comboBoxValues[3]) {
                    GreedyBestFirstSearch greedyBestFirstSearch = new GreedyBestFirstSearch();
                    Node finalState = greedyBestFirstSearch.solve();
                    HomeForm.this.displaySolution(finalState);
                }
            }
        });

    }

    private void displaySolution(Node finalState) {

        HomeForm.this.solutionTextArea.setText("");

        if (finalState == null) {
            this.solutionTextArea.setText("No solution found!");
        } else {
            while (finalState.getParent() != null) {
                this.solutionTextArea.append(finalState.toString());
                finalState = finalState.getParent();
            }
            this.solutionTextArea.append(finalState.toString());
        }

    }

}

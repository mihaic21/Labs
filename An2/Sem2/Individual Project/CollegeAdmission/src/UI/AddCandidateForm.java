package UI;

import Model.Candidate;
import Model.Section;
import Utils.MyException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by mihai on 21.05.2014.
 */
public class AddCandidateForm extends JFrame {
    private JPanel rootPanel;
    private JPanel infoPanel;
    private JLabel cnpLabel;
    private JTextField cnpTextField;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel addressLabel;
    private JLabel bacAverageLabel;
    private JTextField addressTextField;
    private JTextField bacAverageTextField;
    private JLabel highSchoolAverageLabel;
    private JTextField highSchoolTextField;
    private JLabel optionsLabel;
    private JScrollPane optionsTableScrollPane;
    private JTable optionsTable;
    private JButton backButton;
    private JPanel buttonsPanel;
    private JButton addCandidateButton;
    private JPanel optionsPanel;
    private JButton addOptionButton;
    private JLabel selectedOptionsLabel;
    private JPanel optionsButtonPanel;
    private JButton resetOptionsButton;
    private JButton deleteButton;

    private FacultyForm facultyForm;

    private ArrayList<String> selectedOptions = new ArrayList<String>();

    private boolean enableEditing = false;
    private Candidate editableCandidate;


    public AddCandidateForm(FacultyForm facultyForm){

        this.facultyForm = facultyForm;

        this.deleteButton.setVisible(false);

        this.initializeListeners();
        this.populateTable();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.selectedOptionsLabel.setText("Options: ");

    }

    public void setForEdit(Candidate candidate){

        this.editableCandidate = candidate;

        this.cnpTextField.setText(editableCandidate.getCnp());
        this.nameTextField.setText(editableCandidate.getName());
        this.addressTextField.setText(editableCandidate.getAddress());
        this.bacAverageTextField.setText(String.valueOf(editableCandidate.getBacAverage()));
        this.highSchoolTextField.setText(String.valueOf(editableCandidate.getHighSchoolAverage()));
        this.selectedOptions = editableCandidate.getSections();

        StringBuilder stringBuilder = new StringBuilder();
        for (String option : selectedOptions){
            stringBuilder.append(" ");
            stringBuilder.append(option);
        }

        this.selectedOptionsLabel.setText(this.selectedOptionsLabel.getText() + stringBuilder);
        this.selectedOptions = editableCandidate.getSections();

        this.addCandidateButton.setText("Update");

        this.enableEditing = true;
        this.deleteButton.setVisible(true);

    }

    private void addCandidate(){

        try{
            if (enableEditing){

                String cnp = this.cnpTextField.getText();
                String name = this.nameTextField.getText();
                String address = this.addressTextField.getText();
                double bacAverage = Double.parseDouble(this.bacAverageTextField.getText());
                double highSchoolAverage = Double.parseDouble(this.highSchoolTextField.getText());

                Candidate candidate = new Candidate(cnp, name, address, bacAverage, highSchoolAverage, this.selectedOptions);

                this.facultyForm.removeCandidate(editableCandidate);
                this.facultyForm.validateAndAddCandidate(candidate);
                this.dispose();

            } else {
                String cnp = this.cnpTextField.getText();
                String name = this.nameTextField.getText();
                String address = this.addressTextField.getText();
                double bacAverage = Double.parseDouble(this.bacAverageTextField.getText());
                double highSchoolAverage = Double.parseDouble(this.highSchoolTextField.getText());

                Candidate candidate = new Candidate(cnp, name, address, bacAverage, highSchoolAverage, this.selectedOptions);

                this.facultyForm.validateAndAddCandidate(candidate);
                this.dispose();
            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (MyException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void populateTable(){

        Vector columnNames = new Vector();
        columnNames.add("Section");
        columnNames.add("Positions available");

        Vector dataVector = new Vector();
        for (Section section : this.facultyForm.getOptions()){
            Vector row = new Vector();
            row.add(section.getName());
            row.add(section.getPositions());
            dataVector.add(row);
        }

        DefaultTableModel model = new DefaultTableModel(dataVector, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.optionsTable.setModel(model);

    }

    private void initializeListeners(){

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCandidateForm.this.dispose();
            }
        });

        this.addCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCandidateForm.this.addCandidate();
            }
        });

        this.addOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int rowCheck = AddCandidateForm.this.optionsTable.getSelectedRow();
                    if (rowCheck > -1){
                        String option = AddCandidateForm.this.optionsTable.getValueAt(AddCandidateForm.this.optionsTable.getSelectedRow(),0).toString();
                        AddCandidateForm.this.selectedOptionsLabel.setText(AddCandidateForm.this.selectedOptionsLabel.getText() + " " + option);
                        AddCandidateForm.this.selectedOptions.add(option);
                    }

                } catch (NullPointerException e){
                    JOptionPane.showMessageDialog(null, "No option selected");
                }

            }
        });

        this.resetOptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCandidateForm.this.selectedOptions = new ArrayList<String>();
                AddCandidateForm.this.selectedOptionsLabel.setText("Options: ");
            }
        });

        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCandidateForm.this.facultyForm.removeCandidate(editableCandidate);
                AddCandidateForm.this.dispose();
            }
        });

    }

}

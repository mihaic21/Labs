package UI;

import Controller.*;
import Model.AdmittedCandidate;
import Model.Candidate;
import Model.FailedCandidate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by mihai on 07.05.2014.
 */
public class CandidatesForm extends JFrame{
    private JPanel rootPanel;
    private JTable resultsTable;
    private JComboBox selectionComboBox;
    private JButton backButton;
    private JPanel bottomPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JScrollPane tableScrollPane;

    private DefaultTableModel tableModel;

    private HomeForm homeForm;
    private CandidateController controller;

    private String[] comboBoxValues = {"Select Option", "See Admitted Candidates", "See Failed Candidates"};
    private String[] allCandidatesTableColumnValues = {"CNP", "Name", "Address", "Bac average", "High School Average", "Options"};
    private String[] admittedCandidatesTableColumnValues = {"CNP", "Name", "Address", "Final Grade", "Section"};
    private String[] failedCandidatesTableColumnValues = {"CNP", "Name", "Address", "Final Grade"};


    public CandidatesForm(HomeForm homeForm, CandidateController controller) {

        this.homeForm = homeForm;
        this.controller = controller;

        this.initializeListeners();
        this.populateComboBox();

        this.fillTable(this.controller.getAllCandidates());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);

    }



    private void populateComboBox(){

        Vector comboBoxItems = new Vector();
        comboBoxItems.add(comboBoxValues[0]);
        comboBoxItems.add(comboBoxValues[1]);
        comboBoxItems.add(comboBoxValues[2]);

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboBoxItems);
        this.selectionComboBox.setModel(comboBoxModel);

    }

    private void fillTable(ArrayList<Candidate> list){
        if (list != null){
            this.tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Vector columnNames = new Vector();

            Vector dataVector = new Vector();

            if (list.get(0).getClass() == AdmittedCandidate.class){

                columnNames.add(admittedCandidatesTableColumnValues[0]);
                columnNames.add(admittedCandidatesTableColumnValues[1]);
                columnNames.add(admittedCandidatesTableColumnValues[2]);
                columnNames.add(admittedCandidatesTableColumnValues[3]);
                columnNames.add(admittedCandidatesTableColumnValues[4]);

                for (Candidate candidate : list){

                    AdmittedCandidate admittedCandidate = (AdmittedCandidate) candidate;
                    Vector row = new Vector();
                    row.add(admittedCandidate.getCnp());
                    row.add(admittedCandidate.getName());
                    row.add(admittedCandidate.getAddress());
                    row.add(admittedCandidate.getFinalGrade());
                    row.add(admittedCandidate.getFinalSection());
                    dataVector.add(row);

                }


            } else if (list.get(0).getClass().equals(FailedCandidate.class)){

                columnNames.add(failedCandidatesTableColumnValues[0]);
                columnNames.add(failedCandidatesTableColumnValues[1]);
                columnNames.add(failedCandidatesTableColumnValues[2]);
                columnNames.add(failedCandidatesTableColumnValues[3]);

                for (Candidate candidate : list){
                    FailedCandidate failedCandidate = (FailedCandidate) candidate;
                    Vector row = new Vector();
                    row.add(failedCandidate.getCnp());
                    row.add(failedCandidate.getName());
                    row.add(failedCandidate.getAddress());
                    row.add(failedCandidate.getFinalGrade());
                    dataVector.add(row);
                }

            } else {

                columnNames.add(allCandidatesTableColumnValues[0]);
                columnNames.add(allCandidatesTableColumnValues[1]);
                columnNames.add(allCandidatesTableColumnValues[2]);
                columnNames.add(allCandidatesTableColumnValues[3]);
                columnNames.add(allCandidatesTableColumnValues[4]);
                columnNames.add(allCandidatesTableColumnValues[5]);

                for (Candidate candidate : list){
                    Vector row = new Vector();
                    row.add(candidate.getCnp());
                    row.add(candidate.getName());
                    row.add(candidate.getAddress());
                    row.add(candidate.getBacAverage());
                    row.add(candidate.getHighSchoolAverage());
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String option : candidate.getSections()){
                        stringBuilder.append(option);
                        stringBuilder.append(", ");
                    }
                    row.add(stringBuilder);
                    dataVector.add(row);
                }

            }

            this.tableModel.setDataVector(dataVector, columnNames);

            this.resultsTable.setModel(this.tableModel);
        } else {
            this.tableModel = new DefaultTableModel();
            this.resultsTable.setModel(tableModel);
        }

    }

    private void initializeListeners(){

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CandidatesForm.this.setVisible(false);
                CandidatesForm.this.homeForm.setVisible(true);
            }
        });

        this.selectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if (CandidatesForm.this.selectionComboBox.getSelectedItem().toString().equals(CandidatesForm.this.comboBoxValues[0])){
                        if (CandidatesForm.this.controller.getAllCandidates().isEmpty()){
                            CandidatesForm.this.fillTable(null);
                        } else {
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllCandidates());
                        }
                    } else if (CandidatesForm.this.selectionComboBox.getSelectedItem().toString().equals(CandidatesForm.this.comboBoxValues[1])){
                        if (CandidatesForm.this.controller.getAllAdmittedCandidates() == null){
                            CandidatesForm.this.fillTable(null);
                        } else {
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllAdmittedCandidates());
                        }
                    } else if (CandidatesForm.this.selectionComboBox.getSelectedItem().toString().equals(CandidatesForm.this.comboBoxValues[2])){
                        if (CandidatesForm.this.controller.getAllFailedCandidates() == null){
                            CandidatesForm.this.fillTable(null);
                        } else {
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllFailedCandidates());
                        }
                        CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllFailedCandidates());
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }
        });

        this.searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                this.run();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                this.run();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                this.run();
            }

            public void run(){
                try{
                    if (CandidatesForm.this.selectionComboBox.getSelectedItem().toString().equals(CandidatesForm.this.comboBoxValues[0])){
                        if (searchTextField.getText().equals("")){
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllCandidates());
                        } else {
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.
                                    getCandidatesContaining(CandidatesForm.this.searchTextField.getText(), CandidatesForm.this.controller.getAllCandidates()));
                        }
                    } else if (CandidatesForm.this.selectionComboBox.getSelectedItem().toString().equals(CandidatesForm.this.comboBoxValues[1])){
                        if (searchTextField.getText().equals("")){
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllAdmittedCandidates());
                        } else {
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.
                                    getCandidatesContaining(CandidatesForm.this.searchTextField.getText(), CandidatesForm.this.controller.getAllAdmittedCandidates()));
                        }
                    } else if (CandidatesForm.this.selectionComboBox.getSelectedItem().toString().equals(CandidatesForm.this.comboBoxValues[2])){
                        if (searchTextField.getText().equals("")){
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.getAllFailedCandidates());
                        } else {
                            CandidatesForm.this.fillTable(CandidatesForm.this.controller.
                                    getCandidatesContaining(CandidatesForm.this.searchTextField.getText(), CandidatesForm.this.controller.getAllFailedCandidates()));
                        }
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }


            }
        });

    }

}

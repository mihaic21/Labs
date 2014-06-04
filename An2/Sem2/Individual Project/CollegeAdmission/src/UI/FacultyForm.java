package UI;

import Controller.FacultyController;
import Model.Candidate;
import Model.Section;
import Utils.MyException;
import Utils.Validator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by mihai on 07.05.2014.
 */
public class FacultyForm extends JFrame implements Observer{
    private JPanel rootPanel;
    private JTable candidatesTable;
    private JTable sectionsTable;
    private JPanel buttonsPanel;
    private JButton editCandidateButton;
    private JButton editSectionButton;
    private JButton backButton;
    private JScrollPane candidatesTableScrollPane;
    private JScrollPane sectionsTableScrollPane;
    private JButton addCandidateButton;
    private JButton addSectionButton;
    private JLabel candidatesLabel;
    private JLabel sectionsLabel;
    private JButton generateResultsButton;

    private DefaultTableModel candidatesTableModel;
    private DefaultTableModel sectionsTableModel;

    private HomeForm homeForm;
    private FacultyController controller;

    private String[] candidatesTableColumnValues = {"CNP", "Name", "Address", "Bac average", "High School Average", "Options"};
    private String[] sectionsTableColumnValues = {"Name", "Number of positions"};

    public FacultyForm(HomeForm homeForm, FacultyController controller){

        this.homeForm = homeForm;
        this.controller = controller;

        this.controller.addObserver(this);

        this.initializeListeners();

        this.fillCandidatesTable(this.controller.getAllCandidates());
        this.fillSectionsTable(this.controller.getAllSections());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);

    }

    public void validateAndAddCandidate(Candidate candidate) throws MyException {
        this.controller.addCandidate(candidate);
    }

    public void validateAndAddSection(Section section) throws MyException {
        this.controller.addSection(section);
    }

    public void removeCandidate(Candidate candidate){
        this.controller.removeCandidate(candidate);
    }

    public void removeSection(Section section){
        this.controller.removeSection(section);
    }

    public ArrayList<Section> getOptions(){

        return this.controller.getAllSections();

    }

    private void fillCandidatesTable(ArrayList<Candidate> list){

        this.candidatesTableModel = new DefaultTableModel();

        Vector columnNames = new Vector();
        columnNames.add(candidatesTableColumnValues[0]);
        columnNames.add(candidatesTableColumnValues[1]);
        columnNames.add(candidatesTableColumnValues[2]);
        columnNames.add(candidatesTableColumnValues[3]);
        columnNames.add(candidatesTableColumnValues[4]);
        columnNames.add(candidatesTableColumnValues[5]);

        Vector dataVector = new Vector();

        if (list != null){

            for (Candidate candidate : list){

                Vector row = new Vector();
                row.add(candidate.getCnp());
                row.add(candidate.getName());
                row.add(candidate.getAddress());
                row.add(candidate.getBacAverage());
                row.add(candidate.getHighSchoolAverage());
                StringBuilder stringBuilder = new StringBuilder();
                for (String section : candidate.getSections()){
                    stringBuilder.append(section);
                    stringBuilder.append(", ");
                }
                row.add(stringBuilder);
                dataVector.add(row);

            }

        }

        this.candidatesTableModel.setDataVector(dataVector, columnNames);

        this.candidatesTable.setModel(this.candidatesTableModel);


    }

    private void fillSectionsTable(ArrayList<Section> list){

        this.sectionsTableModel = new DefaultTableModel();

        Vector columnNames = new Vector();
        columnNames.add(sectionsTableColumnValues[0]);
        columnNames.add(sectionsTableColumnValues[1]);

        Vector dataVector = new Vector();
        if (list != null){

            for (Section section : list){

                Vector row = new Vector();
                row.add(section.getName());
                row.add(section.getPositions());
                dataVector.add(row);

            }

        }

        this.sectionsTableModel.setDataVector(dataVector, columnNames);

        this.sectionsTable.setModel(this.sectionsTableModel);

    }

    private void generateResults(){

        this.controller.generateResults();
        JOptionPane.showMessageDialog(null, "Results generated!");

    }


    private void initializeListeners(){

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FacultyForm.this.setVisible(false);
                FacultyForm.this.homeForm.setVisible(true);
            }
        });

        this.editCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (FacultyForm.this.candidatesTable.getSelectedRowCount() != 1){
                    JOptionPane.showMessageDialog(null, "One candidate must be selected");
                } else {

                    AddCandidateForm addCandidateForm = new AddCandidateForm(FacultyForm.this);
                    addCandidateForm.setForEdit(FacultyForm.this.controller.getCandidateByCNP(FacultyForm.this.candidatesTable.
                            getValueAt(FacultyForm.this.candidatesTable.getSelectedRow(), 0).toString()));


                }
            }
        });

        this.editSectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (FacultyForm.this.sectionsTable.getSelectedRowCount() != 1){
                    JOptionPane.showMessageDialog(null, "One section must be selected");
                } else {

                    AddSectionForm addSectionForm = new AddSectionForm(FacultyForm.this);
                    addSectionForm.setForEdit(FacultyForm.this.controller.getSectionByName(FacultyForm.this.sectionsTable.
                            getValueAt(FacultyForm.this.sectionsTable.getSelectedRow(), 0).toString()));

                }
            }
        });

        this.addCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCandidateForm addCandidateForm = new AddCandidateForm(FacultyForm.this);
            }
        });

        this.addSectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddSectionForm addSectionForm = new AddSectionForm(FacultyForm.this);
            }
        });

        this.generateResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FacultyForm.this.generateResults();
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {

        ArrayList list = (ArrayList) o;
        this.fillCandidatesTable((ArrayList<Candidate>) list.get(0));
        this.fillSectionsTable((ArrayList<Section>) list.get(1));

    }
}

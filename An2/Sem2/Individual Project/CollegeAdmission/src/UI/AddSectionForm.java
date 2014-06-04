package UI;

import Model.Section;
import Utils.MyException;
import Utils.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mihai on 30.05.2014.
 */
public class AddSectionForm extends JFrame{
    private JPanel rootPanel;
    private JTextField sectionNameTextField;
    private JTextField sectionPositionsTextField;
    private JLabel sectionNameLabel;
    private JLabel sectionPositionsLabel;
    private JPanel buttonsPanel;
    private JPanel infoPanel;
    private JButton addSectionButton;
    private JButton backButton;
    private JButton deleteSectionButton;

    private FacultyForm facultyForm;

    private boolean enableEditing = false;
    private Section editableSection;

    public AddSectionForm(FacultyForm facultyForm){

        this.facultyForm = facultyForm;

        this.deleteSectionButton.setVisible(false);

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void setForEdit(Section section){

        this.editableSection = section;

        this.sectionNameTextField.setText(editableSection.getName());
        this.sectionPositionsTextField.setText(String.valueOf(editableSection.getPositions()));

        this.deleteSectionButton.setVisible(true);
        this.addSectionButton.setText("Update Section");

        this.enableEditing = true;

    }

    private void addSection(){

        try{

            if (enableEditing){

                String name = this.sectionNameTextField.getText();
                int positions = Integer.parseInt(this.sectionPositionsTextField.getText());

                Section section = new Section(name, positions);

                this.facultyForm.removeSection(editableSection);
                this.facultyForm.validateAndAddSection(section);
                this.dispose();

            } else {

                String name = this.sectionNameTextField.getText();
                int positions = Integer.parseInt(this.sectionPositionsTextField.getText());

                Section section = new Section(name, positions);

                this.facultyForm.validateAndAddSection(section);
                this.dispose();

            }


        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid number of positions");
        } catch (MyException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void initializeListeners(){

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddSectionForm.this.dispose();
            }
        });

        this.addSectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddSectionForm.this.addSection();
            }
        });

        this.deleteSectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddSectionForm.this.facultyForm.removeSection(editableSection);
                AddSectionForm.this.dispose();
            }
        });

    }

}

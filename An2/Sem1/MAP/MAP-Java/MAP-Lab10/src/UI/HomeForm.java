package UI;

import Controller.Controller;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by mihai on 2/1/14.
 */
public class HomeForm extends JFrame {

    private Controller ctrl;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JButton viewAllButton = new JButton();
    private JButton exitButton = new JButton();
    private JButton addStudentButton = new JButton();

    private JTable table = new JTable();
    private DefaultTableModel model;

    private JTextField idTextField = new JTextField();
    private JTextField nameTextField = new JTextField("Name");
    private JTextField gradeTextField = new JTextField();
    private JTextField grade2TextField = new JTextField();
    private JTextField grade3TextField = new JTextField();
    private JTextField supervisorTextField = new JTextField();
    private JTextField thesisTextField = new JTextField();

    private JComboBox comboBox = new JComboBox();
    private JComboBox comboBoxRight = new JComboBox();


    public HomeForm (Controller ctrl){

        this.ctrl = ctrl;
        this.setPreferredSize(new Dimension(1000,600));

        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.Y_AXIS));
        //this.leftPanel.setLayout(new GridLayout());
        this.leftPanel.setBorder(BorderFactory.createTitledBorder("View students"));
        this.leftPanel.setOpaque(false);

        //this.rightPanel.setLayout(new GridLayout());
        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));
        this.rightPanel.setOpaque(false);

        this.add(this.leftPanel);
        this.add(this.rightPanel);

        this.addTable();
        this.addButtons();
        this.addComboBox();
        this.addTextFields();

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

        //this.getContentPane().add(this.leftPanel, BorderLayout.LINE_START);
        //this.getContentPane().add(this.rightPanel, BorderLayout.LINE_END);

        this.pack();
        this.setVisible(true);
    }

    private void addTable() {
        this.table.setPreferredSize(new Dimension(400, 200));
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setVisible(true);

        this.fillTable(null);

        leftPanel.add(new JScrollPane(this.table));      //for displaying column headers
    }


    public void fillTable(java.util.List<Student> list) {
        this.table.clearSelection();
        if (list == null) {
            list = this.ctrl.getAllStudentObjects();
        }

        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String className;
        Vector vector = new Vector();
        for (Student student : list) {
            Vector row = new Vector();
            row.add(student.getID());
            className = student.getClass().toString().split("\\.")[1];
            row.add(className);
            row.add(student.getName());
            row.add(student.getGrade());

            if ("UndergraduateStudent".equals(className)){
                row.add(((UndergraduateStudent)student).grade2);
            }else if("GraduateStudent".equals(className)){
                row.add(((GraduateStudent) student).grade2);
                row.add(((GraduateStudent) student).grade3);
                row.add(((GraduateStudent) student).supervisor);
            }else if("PhDStudent".equals(className)){
                row.add(((PhDStudent)student).grade2);
                row.add("");
                row.add(((PhDStudent)student).supervisor);
                row.add(((PhDStudent)student).thesis);
            }
            vector.add(row);
        }

        Vector columnNames = new Vector();
        columnNames.add("ID");
        columnNames.add("Type");
        columnNames.add("Name");
        columnNames.add("Grade");
        columnNames.add("Grade2");
        columnNames.add("Grade3");
        columnNames.add("Supervisor");
        columnNames.add("Thesis");

        this.model.setDataVector(vector, columnNames);

        this.table.setModel(this.model);
    }

    private void addButtons(){
        this.viewAllButton.setText("View All");
        this.viewAllButton.setPreferredSize(new Dimension(80,30));
        this.viewAllButton.setVisible(true);
        this.viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HomeForm.this.fillTable(null);
            }
        });
        this.leftPanel.add(this.viewAllButton);

        this.exitButton.setText("Exit");
        this.exitButton.setPreferredSize(new Dimension(80, 30));
        this.exitButton.setVisible(true);
        this.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        this.leftPanel.add(this.exitButton);

        //right panel
        this.addStudentButton.setPreferredSize(new Dimension(100, 30));
        this.addStudentButton.setText("Add Student");
        this.addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //add student
            }
        });

        this.rightPanel.add(this.addStudentButton);
    }

    private void addComboBox(){
        this.comboBox.setVisible(true);
        this.comboBox.setPreferredSize(new Dimension(100,30));
        this.comboBox.addItem("avg >= 5");
        this.comboBox.addItem("avg < 5");
        this.comboBox.addItem("avg = 10");

        this.comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<Student> filteredList = new ArrayList<Student>();

                if (comboBox.getSelectedItem().equals("avg >= 5")){
                    for (Student student : HomeForm.this.ctrl.getAllStudentObjects()){
                        if (student.average() >= 5){
                            filteredList.add(student);
                        }
                    }
                } else if (comboBox.getSelectedItem().equals("avg < 5")){
                    for (Student student : HomeForm.this.ctrl.getAllStudentObjects()){
                        if (student.average() < 5){
                            filteredList.add(student);
                        }
                    }
                } else if (comboBox.getSelectedItem().equals("avg = 10")){
                    for (Student student : HomeForm.this.ctrl.getAllStudentObjects()){
                        if (student.average() == 10){
                            filteredList.add(student);
                        }
                    }
                }

                HomeForm.this.fillTable(filteredList);
            }
        });

        this.leftPanel.add(this.comboBox);

        //right panel
        this.comboBoxRight.setVisible(true);
        this.comboBoxRight.setPreferredSize(new Dimension(80,30));
        this.comboBoxRight.addItem("Student");
        this.comboBoxRight.addItem("UndergraduateStudent");
        this.comboBoxRight.addItem("GraduateStudent");
        this.comboBoxRight.addItem("PhDStudent");

        this.rightPanel.add(this.comboBoxRight);

    }


    private void addTextFields(){
        this.idTextField.setText("safdsa");
        this.idTextField.setPreferredSize(new Dimension(80,30));
        this.setVisible(false);




        this.rightPanel.add(this.idTextField);
        this.rightPanel.add(this.nameTextField);
        this.rightPanel.add(this.gradeTextField);
        this.rightPanel.add(this.grade2TextField);
        this.rightPanel.add(this.grade3TextField);
        this.rightPanel.add(this.supervisorTextField);
        this.rightPanel.add(this.thesisTextField);
    }

}

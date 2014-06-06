package View;

import Controller.AdminController;
import Model.*;
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
 * Created by mihai on 23.05.2014.
 */
public class AdminForm extends JFrame implements Observer{
    private JPanel rootPanel;
    private JTable table;
    private JScrollPane tableScrollPane;
    private JComboBox selectionComboBox;
    private JPanel buttonsPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton backButton;

    private String[] comboBoxValues = {"Select Option", "Manage Products", "Manage Administrators",
            "Manage Salesmen", "Manage Clients", "Manage Orders"};
    private String[] productColumnNames = {"Code", "Name", "Quantity", "Price"};
    private String[] administratorColumnNames = {"Name", "Password"};
    private String[] salesmanColumnNames = {"Name", "Password"};
    private String[] clientColumnNames = {"CNP", "Name", "Address"};
    private String[] orderColumnNames = {"Order code", "Product Name", "Product Code", "Client Name", "Client CNP", "Quantity"};

    private AdminController controller;
    private LoginForm loginForm;

    public AdminForm(LoginForm loginForm, AdminController controller){
        this.controller = controller;
        this.loginForm = loginForm;

        this.controller.addObserver(this);

        this.initializeListeners();
        this.populateComboBox();
        this.fillTable(null);

        this.setContentPane(rootPanel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addProduct(String code, String name, int quantity, int price) throws MyException {

        Product product = new Product(code, name, quantity, price);
        Validator.validateProduct(product, controller.getAllProducts());
        this.controller.addItem(product);

    }

    public void addAdministrator(String name, String password) throws MyException {

        Administrator administrator = new Administrator(name, password);
        Validator.validateAdministrator(administrator, controller.getAllAdministrators());
        this.controller.addItem(administrator);

    }

    public void addSalesman(String name, String password) throws MyException {

        Salesman salesman = new Salesman(name, password);
        Validator.validateSalesman(salesman, controller.getAllSalesmen());
        this.controller.addItem(salesman);

    }

    public void removeProduct(String code){
        this.controller.removeProduct(code);
    }

    public void removeAdministrator(String username){
        this.controller.removeAdministrator(username);
    }

    public void removeSalesman(String username){
        this.controller.removeSalesman(username);
    }

    public void removeClient(String cnp){
        this.controller.removeClient(cnp);
    }

    public void removeOrder(int id){
        this.controller.removeOrder(id);
    }

    private void fillTable(ArrayList list){

        if (list == null) {

            this.table.setModel(new DefaultTableModel());

        } else if (this.selectionComboBox.getSelectedItem().toString().equals(this.comboBoxValues[1])){

            Vector columnNames = new Vector();
            columnNames.add(productColumnNames[0]);
            columnNames.add(productColumnNames[1]);
            columnNames.add(productColumnNames[2]);
            columnNames.add(productColumnNames[3]);

            Vector dataVector = new Vector();
            for (Product product : this.controller.getAllProducts()){
                Vector row = new Vector();
                row.add(product.getCode());
                row.add(product.getName());
                row.add(product.getQuantity());
                row.add(product.getPrice());
                dataVector.add(row);
            }

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableModel.setDataVector(dataVector, columnNames);

            this.table.setModel(tableModel);

        } else if (this.selectionComboBox.getSelectedItem().toString().equals(this.comboBoxValues[2])){

            Vector columnNames = new Vector();
            columnNames.add(administratorColumnNames[0]);
            columnNames.add(administratorColumnNames[1]);

            Vector dataVector = new Vector();
            for (Administrator administrator : this.controller.getAllAdministrators()){
                Vector row = new Vector();
                row.add(administrator.getUsername());
                row.add(administrator.getPassword());
                dataVector.add(row);
            }

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableModel.setDataVector(dataVector, columnNames);

            this.table.setModel(tableModel);

        } else if (this.selectionComboBox.getSelectedItem().toString().equals(this.comboBoxValues[3])){

            Vector columnNames = new Vector();
            columnNames.add(salesmanColumnNames[0]);
            columnNames.add(salesmanColumnNames[1]);

            Vector dataVector = new Vector();
            for (Salesman salesman : this.controller.getAllSalesmen()){
                Vector row = new Vector();
                row.add(salesman.getUsername());
                row.add(salesman.getPassword());
                dataVector.add(row);
            }

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableModel.setDataVector(dataVector, columnNames);

            this.table.setModel(tableModel);

        } else if (this.selectionComboBox.getSelectedItem().toString().equals(this.comboBoxValues[4])){

            Vector columnNames = new Vector();
            columnNames.add(clientColumnNames[0]);
            columnNames.add(clientColumnNames[1]);
            columnNames.add(clientColumnNames[2]);

            Vector dataVector = new Vector();
            for (Client client : this.controller.getAllClients()){
                Vector row = new Vector();
                row.add(client.getCnp());
                row.add(client.getName());
                row.add(client.getAddress());
                dataVector.add(row);
            }

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableModel.setDataVector(dataVector, columnNames);

            this.table.setModel(tableModel);

        } else if (this.selectionComboBox.getSelectedItem().toString().equals(this.comboBoxValues[5])){

            Vector columnNames = new Vector();
            columnNames.add(orderColumnNames[0]);
            columnNames.add(orderColumnNames[1]);
            columnNames.add(orderColumnNames[2]);
            columnNames.add(orderColumnNames[3]);
            columnNames.add(orderColumnNames[4]);
            columnNames.add(orderColumnNames[5]);

            Vector dataVector = new Vector();
            for (Order order : this.controller.getAllOrders()){
                Vector row = new Vector();
                row.add(order.getCode());
                row.add(order.getProduct().getName());
                row.add(order.getProduct().getCode());
                row.add(order.getClient().getName());
                row.add(order.getClient().getCnp());
                row.add(order.getQuantity());
                dataVector.add(row);
            }

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableModel.setDataVector(dataVector, columnNames);

            this.table.setModel(tableModel);

        }

    }

    private void populateComboBox(){

        Vector comboBoxItems = new Vector();
        comboBoxItems.add(comboBoxValues[0]);
        comboBoxItems.add(comboBoxValues[1]);
        comboBoxItems.add(comboBoxValues[2]);
        comboBoxItems.add(comboBoxValues[3]);
        comboBoxItems.add(comboBoxValues[4]);
        comboBoxItems.add(comboBoxValues[5]);

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboBoxItems);
        this.selectionComboBox.setModel(comboBoxModel);

    }

    private void initializeListeners(){

        this.selectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[0])){
                    AdminForm.this.fillTable(null);
                } else {
                    ArrayList list = new ArrayList();
                    list.add(AdminForm.this.controller.getAllProducts());
                    list.add(AdminForm.this.controller.getAllAdministrators());
                    list.add(AdminForm.this.controller.getAllSalesmen());
                    list.add(AdminForm.this.controller.getAllClients());
                    list.add(AdminForm.this.controller.getAllOrders());
                    AdminForm.this.fillTable(list);
                }
            }
        });

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdminForm.this.loginForm.setVisible(true);
                AdminForm.this.dispose();
            }
        });

        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[0])){
                    JOptionPane.showMessageDialog(null, "Please select category");
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[1])){
                    AddItemForm addItemForm = new AddItemForm(AdminForm.this, "Product");
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[2])){
                    AddItemForm addItemForm = new AddItemForm(AdminForm.this, "Administrator");
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[3])){
                    AddItemForm addItemForm = new AddItemForm(AdminForm.this, "Salesman");
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[4])){
                    AddItemForm addItemForm = new AddItemForm(AdminForm.this, "Client");
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[5])){
                    JOptionPane.showMessageDialog(null, "Only a salesman can place an order");
                }
            }
        });

        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[0])){
                    JOptionPane.showMessageDialog(null, "Please select field");
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[1])){
                    AdminForm.this.removeProduct(AdminForm.this.table.getValueAt(AdminForm.this.table.getSelectedRow(),0).toString());
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[2])){
                    AdminForm.this.removeAdministrator(AdminForm.this.table.getValueAt(AdminForm.this.table.getSelectedRow(),0).toString());
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[3])){
                    AdminForm.this.removeSalesman(AdminForm.this.table.getValueAt(AdminForm.this.table.getSelectedRow(),0).toString());
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[4])){
                    AdminForm.this.removeClient(AdminForm.this.table.getValueAt(AdminForm.this.table.getSelectedRow(), 0).toString());
                } else if (AdminForm.this.selectionComboBox.getSelectedItem().toString().equals(AdminForm.this.comboBoxValues[5])){
                    AdminForm.this.removeOrder(Integer.parseInt(AdminForm.this.table.getValueAt(AdminForm.this.table.getSelectedRow(), 0).toString()));
                }
            }
        });

    }


    @Override
    public void update(Observable observable, Object o) {
        ArrayList list = (ArrayList) o;
        this.fillTable(list);

    }
}

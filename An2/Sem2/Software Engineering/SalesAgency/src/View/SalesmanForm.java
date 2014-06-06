package View;

import Controller.SalesmanController;
import Model.Client;
import Model.Order;
import Model.Product;
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
 * Created by mihai on 22.05.2014.
 */
public class SalesmanForm extends JFrame implements Observer{
    private JPanel rootPanel;
    private JTable productsTable;
    private JScrollPane productsTableScrollPane;
    private JPanel orderPanel;
    private JLabel orderLabel;
    private JTextField quantityTextField;
    private JButton orderButton;
    private JButton backButton;
    private JTable clientsTable;
    private JScrollPane clientsTableScrollPane;
    private JButton viewOrdersButton;
    private JButton addClientButton;

    private SalesmanController controller;
    private LoginForm loginForm;

    private String[] productsTableColumnNames = {"Code", "Name", "Quantity", "Price"};
    private String[] clientsTableColumnNames = {"CNP", "Name", "Address", "Total money spent"};

    public SalesmanForm(LoginForm loginForm, SalesmanController controller) {

        this.loginForm = loginForm;
        this.controller = controller;

        this.controller.addObserver(this);

        this.initializeListeners();

        this.fillProductsTable(this.controller.getAllProducts());
        this.fillClientsTable(this.controller.getAllClients());

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ArrayList<Order> getOrders(){
        return this.controller.getAllOrders();
    }

    public void addClient(String cnp, String name, String address) throws MyException {

        Client client = new Client(cnp, name, address);
        Validator.validateClient(client, this.controller.getAllClients());
        this.controller.addClient(client);

    }

    private void processOrder(){

        try{
            if (productsTable.getSelectedRowCount() != 1 || clientsTable.getSelectedRowCount() != 1){
                JOptionPane.showMessageDialog(null, "One product and one client must be selected");
            } else {

                int quantity = Integer.parseInt(this.quantityTextField.getText());
                if (quantity > Integer.parseInt(this.productsTable.getValueAt(this.productsTable.getSelectedRow(), 2).toString())){
                    JOptionPane.showMessageDialog(null, "Quantity not available");
                }else {
                    this.controller.orderProduct(this.productsTable.getValueAt(this.productsTable.
                            getSelectedRow(), 0).toString(), quantity, this.clientsTable.getValueAt(this.clientsTable.
                            getSelectedRow(), 0).toString());
                }

            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid quantity");
        }
    }


    private void fillProductsTable(ArrayList<Product> list){

        if (list != null){

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Vector dataVector = new Vector();
            for (Object object : list){
                Product product = (Product) object;
                Vector row = new Vector();
                row.add(product.getCode());
                row.add(product.getName());
                row.add(product.getQuantity());
                row.add(product.getPrice());
                dataVector.add(row);
            }

            Vector columnNames = new Vector();
            columnNames.add(this.productsTableColumnNames[0]);
            columnNames.add(this.productsTableColumnNames[1]);
            columnNames.add(this.productsTableColumnNames[2]);
            columnNames.add(this.productsTableColumnNames[3]);

            tableModel.setDataVector(dataVector, columnNames);

            this.productsTable.setModel(tableModel);

        }

    }

    private void fillClientsTable(ArrayList<Client> list){

        if (list != null){

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Vector columnNames = new Vector();
            columnNames.add(clientsTableColumnNames[0]);
            columnNames.add(clientsTableColumnNames[1]);
            columnNames.add(clientsTableColumnNames[2]);
            columnNames.add(clientsTableColumnNames[3]);

            Vector dataVector = new Vector();
            for (Client client : list){
                Vector row = new Vector();
                row.add(client.getCnp());
                row.add(client.getName());
                row.add(client.getAddress());
                row.add(this.controller.getTotalPriceSpentByClient(client));
                dataVector.add(row);
            }

            tableModel.setDataVector(dataVector, columnNames);

            this.clientsTable.setModel(tableModel);

        }

    }

    private void initializeListeners(){

        this.orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SalesmanForm.this.processOrder();
            }
        });

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SalesmanForm.this.dispose();
                SalesmanForm.this.loginForm.setVisible(true);
            }
        });

        this.viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOrdersForm viewOrdersForm = new ViewOrdersForm(SalesmanForm.this);
            }
        });

        this.addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddItemForm addItemForm = new AddItemForm(SalesmanForm.this, "Client");
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList list = (ArrayList) o;
        this.fillProductsTable((ArrayList<Product>) list.get(0));
        this.fillClientsTable((ArrayList<Client>) list.get(1));
    }
}

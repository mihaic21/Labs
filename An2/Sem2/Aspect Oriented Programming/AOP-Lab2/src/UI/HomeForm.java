package UI;

import Controller.Controller;
import Model.Product;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by mihai on 4/24/14.
 */
public class HomeForm extends JFrame implements Observer {
    /*
    private JButton exitButton = new JButton();
    private JPanel rootPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JTable productsTable = new JTable();
    private JTextField searchTextBox = new JTextField();
    private JPanel searchPanel = new JPanel();
    private JLabel searchLabel = new JLabel();
    private JScrollPane productsTablePane = new JScrollPane();
    private JButton orderButton = new JButton();
    private JTextField quantityTextField = new JTextField();
    private JLabel quantityLabel = new JLabel();
    */

    private JButton exitButton;
    private JPanel rootPanel;
    private JPanel buttonsPanel;
    private JTable productsTable;
    private JTextField searchTextBox;
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JScrollPane productsTablePane;
    private JButton orderButton;
    private JTextField quantityTextField;
    private JLabel quantityLabel;

    private DefaultTableModel model;

    private Controller controller;


    public HomeForm() throws HeadlessException {
    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("HomeForm");
//        frame.setContentPane(new HomeForm().rootPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

    public HomeForm(Controller controller) {

        this.controller = controller;
        this.controller.addObserver(this);

        this.fillTable(null);
        this.initializeListeners();

        //this.setContentPane(rootPanel);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        //this.setVisible(true);

    }

    private void fillTable(ArrayList<Product> list) {

        if (list == null) {
            list = this.controller.getAllProducts();
        }

        this.productsTable.clearSelection();

        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Vector dataVector = new Vector();
        for (Product product : list) {
            Vector row = new Vector();
            row.add(product.getCode());
            row.add(product.getName());
            row.add(product.getQuantity());
            row.add(product.getPrice());
            dataVector.add(row);
        }

        Vector columnNames = new Vector();
        columnNames.add("Code");
        columnNames.add("Name");
        columnNames.add("Quantity");
        columnNames.add("Price");

        this.model.setDataVector(dataVector, columnNames);

        this.productsTable.setModel(this.model);

    }

    private void initializeListeners() {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int quantity = Integer.parseInt(HomeForm.this.quantityTextField.getText());
                    String id = HomeForm.this.productsTable.getValueAt(HomeForm.this.productsTable.getSelectedRow(), 0).toString();
                    HomeForm.this.controller.orderProduct(id, quantity);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }
        });

        searchTextBox.getDocument().addDocumentListener(new DocumentListener() {
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

            public void run() {
                if (searchTextBox.getText() == "") {
                    HomeForm.this.fillTable(null);
                } else {
                    HomeForm.this.fillTable(HomeForm.this.controller.getProductsContaining(searchTextBox.getText()));
                }
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Product> productArrayList = (ArrayList<Product>) o;

        this.fillTable(productArrayList);
    }

}

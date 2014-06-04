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
import java.util.Vector;

/**
 * Created by mihai on 19.05.2014.
 */
public class HomeForm extends JFrame{

    private Controller controller;

    private JPanel rootPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private JScrollPane productsTablePane = new JScrollPane();
    private JTable productsTable = new JTable();

    private JTextField searchTextField = new JTextField();
    private JTextField quantityTextField = new JTextField();

    private JButton orderButton = new JButton("Order");
    private JButton exitButton = new JButton("Exit");

    private JLabel quantityLabel = new JLabel("Select product, enter quantity and press \"Order\"");
    private JLabel searchLabel = new JLabel("Type product name. Leave empty for all");

    private DefaultTableModel model = new DefaultTableModel();



    public HomeForm(Controller controller){

        this.controller = controller;

        this.initializeComponents();

        this.initializeListeners();

        this.fillTable(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initializeComponents() {

        this.rootPanel.setPreferredSize(new Dimension(700, 600));
        this.setContentPane(rootPanel);

        this.rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));

        this.productsTablePane.setViewportView(this.productsTable);

        this.searchTextField.setMaximumSize(new Dimension(500,100));
        this.quantityTextField.setMaximumSize(new Dimension(500, 100));

        this.searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));
        this.searchPanel.add(searchLabel);
        this.searchPanel.add(searchTextField);

        this.buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        this.buttonsPanel.add(quantityLabel);
        this.buttonsPanel.add(quantityTextField);
        this.buttonsPanel.add(orderButton);
        this.buttonsPanel.add(exitButton);

        this.rootPanel.add(productsTablePane);
        this.rootPanel.add(searchPanel);
        this.rootPanel.add(buttonsPanel);

    }


    public void fillTable(ArrayList<Product> list) {

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

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                if (searchTextField.getText() == "") {
                    HomeForm.this.fillTable(null);
                } else {
                    HomeForm.this.fillTable(HomeForm.this.controller.getProductsContaining(searchTextField.getText()));
                }
            }
        });

    }

}

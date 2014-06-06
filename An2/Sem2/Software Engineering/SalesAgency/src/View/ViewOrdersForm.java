package View;


import Model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by mihai on 31.05.2014.
 */
public class ViewOrdersForm extends JFrame{
    private JPanel rootPanel;
    private JTable ordersTable;
    private JScrollPane ordersTableScrollPane;
    private JPanel buttonsPanel;
    private JButton backButton;

    private SalesmanForm salesmanForm;

    private String[] tableColumnNames = {"Order code", "Product Name", "Product Code", "Client Name", "Client CNP", "Quantity"};

    public ViewOrdersForm(SalesmanForm salesmanForm){

        this.salesmanForm = salesmanForm;

        this.initializeListeners();

        this.fillTable(salesmanForm.getOrders());

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void fillTable(ArrayList<Order> list) {

        if (list != null){

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Vector columnNames = new Vector();
            columnNames.add(tableColumnNames[0]);
            columnNames.add(tableColumnNames[1]);
            columnNames.add(tableColumnNames[2]);
            columnNames.add(tableColumnNames[3]);
            columnNames.add(tableColumnNames[4]);
            columnNames.add(tableColumnNames[5]);

            Vector dataVector = new Vector();
            for (Order order : list){
                Vector row = new Vector();
                row.add(order.getCode());
                row.add(order.getProduct().getName());
                row.add(order.getProduct().getCode());
                row.add(order.getClient().getName());
                row.add(order.getClient().getCnp());
                row.add(order.getQuantity());
                dataVector.add(row);
            }

            tableModel.setDataVector(dataVector, columnNames);

            this.ordersTable.setModel(tableModel);
        }

    }

    private void initializeListeners(){

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewOrdersForm.this.dispose();
            }
        });

    }

}

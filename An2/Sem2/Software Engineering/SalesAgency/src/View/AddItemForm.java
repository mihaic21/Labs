package View;

import Utils.MyException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mihai on 25.05.2014.
 */
public class AddItemForm extends JFrame{
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel infoPanel;
    private JPanel buttonsPanel;
    private JButton addButton;
    private JButton backButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    private AdminForm adminForm;
    private SalesmanForm salesmanForm;
    private String itemType;

    public AddItemForm(AdminForm adminForm, String itemType){

        this.adminForm = adminForm;
        this.itemType = itemType;

        this.initializeListeners();
        this.initializeItems();

        this.setContentPane(rootPanel);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public AddItemForm(SalesmanForm salesmanForm, String itemType){

        this.salesmanForm = salesmanForm;

        this.itemType = itemType;

        this.initializeListeners();
        this.initializeItems();

        this.setContentPane(rootPanel);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void addItem() throws MyException {

        if (itemType.equals("Product")){

            if (textField1.getText().isEmpty() || textField2.getText().isEmpty()
                    || textField3.getText().isEmpty() || textField4.getText().isEmpty()){
                throw new MyException("Field cannot be empty");
            }

            this.adminForm.addProduct(textField1.getText(), textField2.getText(),
                    Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText()));

        } else if (itemType.equals("Administrator")){

            if (textField1.getText().isEmpty() || textField2.getText().isEmpty()){
                throw new MyException("Field cannot be empty");
            }

            this.adminForm.addAdministrator(textField1.getText(), textField2.getText());

        } else if (itemType.equals("Salesman")){

            if (textField1.getText().isEmpty() || textField2.getText().isEmpty()){
                throw new MyException("Field cannot be empty");
            }

            this.adminForm.addSalesman(textField1.getText(), textField2.getText());

        } else if (itemType.equals("Client")){

            if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty()){
                throw new MyException("Field cannot be empty");
            }

            this.salesmanForm.addClient(textField1.getText(), textField2.getText(), textField3.getText());

        }

    }

    private void initializeItems(){

        if (this.itemType.equals("Product")){

            label1.setText("Code");
            label2.setText("Name");
            label3.setText("Quantity");
            label4.setText("Price");

        } else if (this.itemType.equals("Administrator")){

            label1.setText("Username");
            label2.setText("Password");
            label3.setVisible(false);
            label4.setVisible(false);
            textField3.setVisible(false);
            textField4.setVisible(false);

        } else if (this.itemType.equals("Salesman")){

            label1.setText("Username");
            label2.setText("Password");
            label3.setVisible(false);
            label4.setVisible(false);
            textField3.setVisible(false);
            textField4.setVisible(false);

        } else if (this.itemType.equals("Client")){

            label1.setText("CNP");
            label2.setText("Name");
            label3.setText("Address");
            label4.setVisible(false);
            textField4.setVisible(false);

        }

    }

    private void initializeListeners(){

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddItemForm.this.dispose();
            }
        });

        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    AddItemForm.this.addItem();
                    AddItemForm.this.dispose();
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

    }

}

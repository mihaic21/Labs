import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shobo on 23.10.2014.
 */
public class PermutationForm extends JFrame{

    private JPanel rootPanel;
    private JTextField keyTextField;
    private JTextArea plainTextArea;
    private JTextArea cipherTextArea;
    private JButton encryptButton;
    private JButton decryptButton;
    private JCheckBox customCheckBox;
    private JTextField alphabetTextField;

    private String defaultAlphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Z Y  ";

    public PermutationForm(){

        this.initializeListeners();

        this.alphabetTextField.setEnabled(false);

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    private void initializeListeners(){

        this.encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PermutationForm.this.encrypt();
            }
        });

        this.decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PermutationForm.this.decrypt();
            }
        });

        this.customCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (PermutationForm.this.customCheckBox.isSelected()){
                    PermutationForm.this.alphabetTextField.setEnabled(true);
                } else {
                    PermutationForm.this.alphabetTextField.setEnabled(false);
                }
            }
        });

    }

    private void encrypt(){
        try {
            String alphabet;
            String key;
            String plain;

            if (this.customCheckBox.isSelected()) {  //custom alphabet
                alphabet = this.alphabetTextField.getText();
            } else {    //default alphabet
                alphabet = this.defaultAlphabet;
            }

            key = this.keyTextField.getText();
            plain = this.plainTextArea.getText();

            if (!PermutationCipher.validateKey(key.split(" "))) {
                throw new Exception("Invalid Key");
            }
            if (!PermutationCipher.validateText(alphabet, plain)) {
                throw new Exception("Invalid text");
            }

            this.cipherTextArea.setText(PermutationCipher.encrypt(key.split(" "), PermutationCipher.split(key.split(" ").length, plain)));

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decrypt(){

        try{

            String key;

            key = this.keyTextField.getText();

            if (!PermutationCipher.validateKey(key.split(" "))) {
                throw new Exception("Invalid key");
            }

            this.plainTextArea.setText(PermutationCipher.encrypt(PermutationCipher.invert(key.split(" ")),
                    PermutationCipher.split(PermutationCipher.invert(key.split(" ")).length, this.cipherTextArea.getText())));

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}

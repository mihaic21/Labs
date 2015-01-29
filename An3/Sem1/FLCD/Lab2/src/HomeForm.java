import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by shobo on 24.11.2014.
 */
public class HomeForm extends JFrame{
    private JPanel rootPanel;
    private JTextField nTextField;
    private JTextField tTextField;
    private JTextField pTextField;
    private JTextField sTextField;
    private JButton addNonTerminalButton;
    private JButton addTerminalButton;
    private JButton addProductionButton;
    private JButton addStartGrammarButton;
    private JButton checkIfRegularButton;
    private JTextField nSetTextField;
    private JTextField pSetTextField;
    private JTextField tSetTextField;
    private JButton grammarToAutomataButton;
    private JTextField qTextField;
    private JTextField tAlphabetTextField;
    private JTextField s0TextField;
    private JTextField fTextField;
    private JButton addStateButton;
    private JButton addAlphabetButton;
    private JButton addStartAutomataButton;
    private JButton addFinalStateButton;
    private JTextField qSetTextField;
    private JTextField tAlphabetSetTextField;
    private JTextField fSetTextField;
    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextField throughTextField;
    private JButton addTransitionButton;
    private JButton automataToGrammarButton;
    private JTextArea transitionsTextArea;
    private JTextField s0ViewTextField;
    private JTextField sViewTextField;

    //Grammar
    private ArrayList<String> grammarNonTerminalList = new ArrayList<String>();
    private ArrayList<String> grammarTerminalList = new ArrayList<String>();
    private ArrayList<String> grammarProductionList = new ArrayList<String>();
    private String grammarStartSymbol;

    //Automata
    private ArrayList<String> automataStateList = new ArrayList<String>();
    private ArrayList<String> automataAlphabetList = new ArrayList<String>();
    private ArrayList<String> automataFinalStateList = new ArrayList<String>();
    private String automataStartSymbol;

    //Transitions
    private ArrayList<String> nodeInitialList = new ArrayList<String>();
    private ArrayList<String> nodeFinalList = new ArrayList<String>();
    private ArrayList<String> archList = new ArrayList<String>();

    private Boolean sGoesInE = false;


    public HomeForm(){

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void addNonTerminal(){
        if (this.nTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid N");
        } else {
            this.grammarNonTerminalList.add(this.nTextField.getText());
            this.nSetTextField.setText(this.grammarNonTerminalList.toString());
            this.nTextField.setText("");
        }
    }

    private void addTerminal(){
        if (this.tTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid T");
        } else {
            this.grammarTerminalList.add(this.tTextField.getText());
            this.tSetTextField.setText(this.grammarTerminalList.toString());
            this.tTextField.setText("");
        }
    }

    private void addProduction(){
        if (this.pTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid P");
        } else {
            this.grammarProductionList.add(this.pTextField.getText());
            this.pSetTextField.setText(this.grammarProductionList.toString());
            this.pTextField.setText("");
        }
    }

    private void addStartGrammar(){
        if (this.sTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid S");
        } else {
            this.grammarStartSymbol = this.sTextField.getText();
            this.grammarNonTerminalList.add(this.grammarStartSymbol);
            this.nSetTextField.setText(this.grammarNonTerminalList.toString());
            this.sViewTextField.setText(grammarStartSymbol);
            this.sTextField.setEnabled(false);
            this.addStartGrammarButton.setEnabled(false);
        }
    }

    private void displayCheckIfRegular(){

        if (!checkIfRegular()){
            JOptionPane.showMessageDialog(null, "Not regular");
        } else {
            JOptionPane.showMessageDialog(null, "Regular");
        }

    }

    private boolean checkIfRegular(){

        String[] wholeRuleArray;
        String[] rightHandSideArray;
        String firstSeparator = "->";
        String secondSeparator = "\\|";

        for (String production : grammarProductionList) {

            wholeRuleArray = production.split(firstSeparator);
            wholeRuleArray = this.removeEmptyEntries(wholeRuleArray);
            rightHandSideArray = wholeRuleArray[1].split(secondSeparator);
            rightHandSideArray = this.removeEmptyEntries(rightHandSideArray);

            for (String item : rightHandSideArray){

                if (item == null){
                    break;
                }

                if (item.length() == 1 && !isTerminal(item) && !"E".equals(item)){
                        return false;
                }

                if (item.length() == 2){
                    if (!isTerminal(String.valueOf(item.charAt(0)))){
                        return false;
                    }
                    if (!isNonTerminal(String.valueOf(item.charAt(1)))){
                        return false;
                    }
                }

                if (item.length() > 2){
                    return false;
                }

                if (item.contains("E")){
                    if (!wholeRuleArray[0].equals(grammarStartSymbol)){
                        return false;
                    } else {
                        if (wholeRuleArray[1].contains(grammarStartSymbol)){
                            return false;
                        }
                        sGoesInE = true;
                    }
                }
            }
        }
        return true;
    }

    private boolean isNonTerminal(String item){
        for (String nonTerminal : grammarNonTerminalList){
            if (nonTerminal.equals(item)){
                return true;
            }
        }
        return false;
    }

    private boolean isTerminal(String item){
        for (String terminal : grammarTerminalList){
            if (terminal.equals(item)){
                return true;
            }
        }
        return false;
    }

    private void addState(){
        if (this.qTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid Q");
        } else {
            this.automataStateList.add(this.qTextField.getText());
            this.qSetTextField.setText(this.automataStateList.toString());
            this.qTextField.setText("");
        }
    }

    private void addAlphabet(){
        if (this.tAlphabetTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid T");
        } else {
            this.automataAlphabetList.add(this.tAlphabetTextField.getText());
            this.tAlphabetSetTextField.setText(this.automataAlphabetList.toString());
            this.tAlphabetTextField.setText("");
        }
    }

    private void addStartAutomata(){
        if (this.s0TextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid S");
        } else {
            this.automataStartSymbol = this.s0TextField.getText();
            this.s0ViewTextField.setText(automataStartSymbol);
            this.s0TextField.setEnabled(false);
            this.addStartAutomataButton.setEnabled(false);
        }
    }

    private void addFinalState(){
        if (this.fTextField.getText().length() < 1){
            JOptionPane.showMessageDialog(null, "Invalid F");
        } else {
            this.automataFinalStateList.add(this.fTextField.getText());
            this.fSetTextField.setText(this.automataFinalStateList.toString());
            this.fTextField.setText("");
        }
    }

    private void addTransition(){
        if ((fromTextField.getText().length() < 1) || (toTextField.getText().length() < 1) || (throughTextField.getText().length() < 1)){
            JOptionPane.showMessageDialog(null, "Invalid input");
        } else {
            nodeInitialList.add(fromTextField.getText());
            nodeFinalList.add(toTextField.getText());
            archList.add(throughTextField.getText());
            transitionsTextArea.append("From " + fromTextField.getText()
                    + " through " + throughTextField.getText()
                    + " to " + toTextField.getText() + "\n");
            fromTextField.setText("");
            toTextField.setText("");
            throughTextField.setText("");
        }
    }

    private String[] removeEmptyEntries(String[] givenArray){
        String[] result = new String[50];
        int length = 0;
        for (String currentString : givenArray){
            if (currentString != ""){
                result[length] = currentString;
                length++;
            }
        }
        return result;
    }

    private void grammarToAutomata(){

        this.automataStateList = new ArrayList<String>();
        this.automataAlphabetList = new ArrayList<String>();
        this.automataFinalStateList = new ArrayList<String>();

        transitionsTextArea.setText("");

        //states = non-terminals
        for (String item : grammarNonTerminalList){
            automataStateList.add(item);
        }
        //automataStateList.add(grammarStartSymbol);
        automataStateList.add("K");
        qSetTextField.setText(automataStateList.toString());

        //same start symbol
        automataStartSymbol = grammarStartSymbol;
        s0ViewTextField.setText(automataStartSymbol);

        //alphabet = terminals
        for (String item : grammarTerminalList){
            automataAlphabetList.add(item);
        }
        automataFinalStateList.add("K");
        tAlphabetSetTextField.setText(automataAlphabetList.toString());

        this.checkIfRegular();

        //if S->E, start symbol is also final state
        if (sGoesInE){
            automataFinalStateList.add(grammarStartSymbol);
            fSetTextField.setText(automataFinalStateList.toString());
        }

        this.nodeFinalList = new ArrayList<String>();
        this.nodeInitialList = new ArrayList<String>();
        this.archList = new ArrayList<String>();

        String[] wholeRuleArray;
        String[] rightHandSideArray;
        String firstSeparator = "->";
        String secondSeparator = "\\|";

        //transform the productions
        for (String production : grammarProductionList){

            wholeRuleArray = production.split(firstSeparator);
            wholeRuleArray = this.removeEmptyEntries(wholeRuleArray);
            rightHandSideArray = wholeRuleArray[1].split(secondSeparator);
            rightHandSideArray = this.removeEmptyEntries(rightHandSideArray);

            for (String item : rightHandSideArray){

                if (item == null){
                    break;
                }

                if (!item.equals("E")){

                    nodeInitialList.add(wholeRuleArray[0]);

                    if (item.length() == 2){
                        nodeFinalList.add(String.valueOf(item.charAt(1)));
                        archList.add(String.valueOf(item.charAt(0)));
                    } else {
                        nodeFinalList.add("K");
                        archList.add(String.valueOf(item.charAt(0)));
                    }
                }
            }
        }

        for (int i = 0; i < nodeInitialList.size(); i++){
            transitionsTextArea.append("From " + nodeInitialList.get(i) +
                                " through " + archList.get(i) +
                                " to " + nodeFinalList.get(i) + "\n");
        }

    }

    private void automataToGrammar(){

        this.grammarNonTerminalList = new ArrayList<String>();
        this.grammarTerminalList = new ArrayList<String>();
        this.grammarProductionList = new ArrayList<String>();

        //states = non-terminals
        for (String item : automataStateList){
            grammarNonTerminalList.add(item);
        }
        nSetTextField.setText(grammarNonTerminalList.toString());

        //same start symbol
        grammarStartSymbol = automataStartSymbol;
        sViewTextField.setText(grammarStartSymbol);

        //alphabet = terminals
        for (String item : automataAlphabetList){
            grammarTerminalList.add(item);
        }
        tSetTextField.setText(grammarTerminalList.toString());

        //form the productions
        for (String item : automataStateList){

            String production = item + "->";
            for (int i = 0; i < nodeInitialList.size(); i++){
                if (nodeInitialList.get(i).equals(item)){
                    production += archList.get(i) + nodeFinalList.get(i) + "|";
                    if (isFinalState(nodeFinalList.get(i))) {
                        production += archList.get(i) + "|";
                    }
                }
            }
            if (isFinalState(automataStartSymbol) && (item.equals(automataStartSymbol))){
                production += "E";
            }
            grammarProductionList.add(production);
        }
        this.pSetTextField.setText(grammarProductionList.toString());
    }

    private boolean isFinalState(String state){
        for (String item : automataFinalStateList){
            if (item.equals(state)){
                return true;
            }
        }
        return false;
    }

    private void initializeListeners(){

        this.addNonTerminalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addNonTerminal();
            }
        });

        this.addTerminalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addTerminal();
            }
        });

        this.addProductionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addProduction();
            }
        });

        this.addStartGrammarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addStartGrammar();
            }
        });

        this.checkIfRegularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayCheckIfRegular();
            }
        });

        this.addStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addState();
            }
        });

        this.addAlphabetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addAlphabet();
            }
        });

        this.addStartAutomataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addStartAutomata();
            }
        });

        this.addFinalStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFinalState();
            }
        });

        this.addTransitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addTransition();
            }
        });

        this.grammarToAutomataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grammarToAutomata();
            }
        });

        this.automataToGrammarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automataToGrammar();
            }
        });

    }

}

package UI;

import SearchMethods.EvolutionaryAlgorithm;
import SearchMethods.ParticleSwarmOptimization;
import Utils.MyException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by mihai on 04.06.2014.
 */
public class HomeForm extends JFrame{
    private JPanel rootPanel;
    private JPanel eaPanel;
    private JPanel psoPanel;
    private JTextArea firstTextArea;
    private JPanel eaInfoPanel;
    private JButton solveEAButton;
    private JTextField populationTextField;
    private JTextField crossoverTextField;
    private JTextField mutationTextField;
    private JTextField generationsTextField;
    private JTextArea secondTextArea;
    private JPanel psoInfoPanel;
    private JTextField swarmTextField;
    private JTextField cognitiveTextField;
    private JTextField socialTextField;
    private JTextField inertiaTextField;
    private JTextField iterationsTextField;
    private JButton solvePSOButton;

    public HomeForm(){

        this.initializeListeners();

        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void solveEA(){

        try{
            firstTextArea.setText("");
            secondTextArea.setText("");
            EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm();
            int populationSize = Integer.parseInt(populationTextField.getText());
            double crossover = Double.parseDouble(crossoverTextField.getText());
            double mutation = Double.parseDouble(mutationTextField.getText());
            int generationNumber = Integer.parseInt(generationsTextField.getText());

            ArrayList<String> generations = evolutionaryAlgorithm.solve(populationSize, crossover, mutation, generationNumber);

            int size = generations.get(0).length();

            for (int i = 0; i < generations.size(); i++){
                String result1 = "Generation " + (i+1) + " : ";
                String result2 = "Generation " + (i+1) + " : ";

                for (int j = 0; j < size; j++){
                    if (generations.get(i).charAt(j) == '0'){
                        result1 += " " + j + " ";
                    }
                    else {
                        result2 += " " + j + " ";
                    }
                }

                firstTextArea.append(result1 + "\n");
                secondTextArea.append(result2 + "\n");
            }

        } catch (MyException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void solvePSO(){

        try{

            firstTextArea.setText("");
            secondTextArea.setText("");

            int swarm = Integer.parseInt(swarmTextField.getText());
            int cognitive = Integer.parseInt(cognitiveTextField.getText());
            int social = Integer.parseInt(socialTextField.getText());
            int inertia = Integer.parseInt(inertiaTextField.getText());
            int noOfIterations = Integer.parseInt(iterationsTextField.getText());

            ParticleSwarmOptimization particleSwarmOptimization = new ParticleSwarmOptimization();
            ParticleSwarmOptimization.setCognitiveCoefficient(cognitive);
            ParticleSwarmOptimization.setSocialCoefficient(social);
            ParticleSwarmOptimization.setInertiaCoefficient(inertia);

            ArrayList<String> iterations = particleSwarmOptimization.solve(swarm, noOfIterations);

            int size = iterations.get(0).length();

            for (int i = 0; i < iterations.size(); i++){
                String result1 = "Iteration " + (i + 1) + " : ";
                String result2 = "Iteration " + (i + 1) + " : ";

                for (int j = 0; j < size; j++){
                    if (iterations.get(i).charAt(j) == '0'){
                        result1 += " " + j + " ";
                    } else {
                        result2 +=" " + j + " ";
                    }
                }

                firstTextArea.append(result1 + "\n");
                secondTextArea.append(result2 + "\n");
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void initializeListeners(){

        this.solveEAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                solveEA();
            }
        });

        this.solvePSOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                solvePSO();
            }
        });

    }

}

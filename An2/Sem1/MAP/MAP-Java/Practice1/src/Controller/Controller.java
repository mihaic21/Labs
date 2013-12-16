package Controller;

import Model.Beverage;
import Model.Coffee;
import Model.Juice;
import Model.Tea;
import Repository.Repository;
import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/15/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    private Repository repo;

    public Controller(Repository repo){
        this.repo = repo;
        this.readFromFile("input.txt");
        this.writeToFile(this.whatToWrite());
    }

    public void readFromFile(String fileName){
        try{
            ArrayList<Beverage> newElements = new ArrayList<Beverage>();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if ("Tea".equals(tokens[0]))
                    newElements.add(new Tea(tokens[1]));
                else if ("Coffee".equals(tokens[0]))
                    newElements.add(new Coffee(tokens[1]));
                else if ("Juice".equals(tokens[0]))
                    newElements.add(new Juice(tokens[1]));
                else
                    throw new MyException("Error reading from file!");

                this.repo.replaceContent(newElements);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());


        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Beverage> whatToWrite(){
        try{
            ArrayList<Beverage> copy = new ArrayList<Beverage>(repo.getElements());
            ArrayList<Beverage> result = new ArrayList<Beverage>();
            for (Beverage elem : copy){
                if (!elem.canBeDecoratedWithWhippedCream())
                    result.add(elem);
            }
            return result;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void writeToFile(ArrayList<Beverage> toWrite){
        try {
            repo.writeToFile(toWrite);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

}
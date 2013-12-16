package Controller;

import Model.Can;
import Model.JuiceBottle;
import Model.Product;
import Model.Sandwich;
import Repository.Repository;
import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/16/13
 * Time: 2:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    private Repository<Product> repo;

    public Controller(Repository repo){
        this.repo = repo;
        this.readFromFile("input.txt");
        this.writeToFile(this.whatToWrite());
    }

    public void readFromFile(String fileName){
        try{
            ArrayList<Product> newElements = new ArrayList<Product>();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if ("Can".equals(tokens[0]))
                    newElements.add(new Can(tokens[1],Integer.parseInt(tokens[2])));
                else if ("JuiceBottle".equals(tokens[0]))
                    newElements.add(new JuiceBottle(tokens[1],Integer.parseInt(tokens[2])));
                else if ("Sandwich".equals(tokens[0]))
                    newElements.add(new Sandwich(tokens[1],Integer.parseInt(tokens[2])));
                else
                    throw new MyException("Error reading from file!");

                this.repo.replaceContent(newElements);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(ArrayList<Product> toWrite){
        try {
            if (toWrite.size() < 0)
                throw new MyException("No elements to write");
            repo.writeToFile(toWrite);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Product> whatToWrite(){
        try{
            ArrayList<Product> copy = new ArrayList<Product>(repo.getElements());
            ArrayList<Product> result = new ArrayList<Product>();
            for (Product elem : copy){
                if (elem.getExpirationDate() > 15){
                    result.add(elem);
                }
            }
            return result;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

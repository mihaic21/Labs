package Controller;

import Model.Cires;
import Model.Mar;
import Model.Par;
import Model.Pom;
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
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    private Repository repo;

    public Controller(Repository repo) {
        this.repo = repo;
        try {
            this.readFromFile("input.txt");
            this.writeToFile(this.whatToWrite());
        } catch (MyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void writeToFile(ArrayList<Pom> toWrite) throws MyException {
        try{
            if (toWrite.size() < 0)
                throw new MyException("No elements to write!");
            repo.writeToFile(toWrite);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Pom> whatToWrite(){
        try{
            ArrayList<Pom> copy = new ArrayList<Pom>(repo.getElements());
            ArrayList<Pom> result = new ArrayList<Pom>();
            for (Pom elem : copy){
                if ((!"Cires".equals(elem.getMyClass())) && (elem.getAge() > 3))
                    result.add(elem);
            }
            return result;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void readFromFile(String fileName){
        try{
            ArrayList<Pom> newElements = new ArrayList<Pom>();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if ("Cires".equals(tokens[0]))
                    newElements.add(new Cires(tokens[1],Integer.parseInt(tokens[2])));
                else if ("Mar".equals(tokens[0]))
                    newElements.add(new Mar(tokens[1],Integer.parseInt(tokens[2])));
                else if ("Par".equals(tokens[0]))
                    newElements.add(new Par(tokens[1],Integer.parseInt(tokens[2])));
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

}

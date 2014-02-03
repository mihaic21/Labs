package Controller;

import Model.Cake;
import Model.Coffee;
import Model.Goodies;
import Model.Sandwich;
import Repository.Repository;
import Repository.RepoInterface;
import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mihai on 2/3/14.
 */
public class Controller {

    private RepoInterface<Goodies> repo;

    public Controller(){}

    public Controller(RepoInterface<Goodies> repo){
        this.repo = repo;
    }

    public String getAllGoodies(){
        String result="\n";
        try{

            for (Goodies good : this.repo.getElements()){
                result += good.toString() + "\n";
            }

        }catch (NullPointerException ex){
            return "no goodies!";
        }
        return result;
    }

    public String addGoodie(String typeOfCoffee, String typeOfServing, int price){

        this.repo.addElement(new Coffee(typeOfCoffee, typeOfServing, price));
        return "Added ok";
    }

    public String addGoodie(int weight, String content, String typeOfServing, int price){

        this.repo.addElement(new Sandwich(weight,content,typeOfServing,price));
        return "added ok";
    }

    public String addGoodie(String shape, String typeOfCake, String typeOfServing, int price){

        this.repo.addElement(new Cake(shape,typeOfCake,price));
        //type of serving is COLD!!!
        return "Added ok";
    }

    public void writeToFile(ArrayList<Goodies> toWrite) throws MyException {
        try{
            if (toWrite.size() < 0)
                throw new MyException("No elements to write!");
            repo.writeToFile(toWrite);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile(String fileName){
        try{
            ArrayList<Goodies> newElements = new ArrayList<Goodies>();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if ("Coffee".equals(tokens[0]))
                    newElements.add(new Coffee(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                else if ("Sandwich".equals(tokens[0]))
                    newElements.add(new Sandwich(Integer.parseInt(tokens[1]),tokens[2],tokens[3],Integer.parseInt(tokens[4])));
                else if ("Cake".equals(tokens[0]))
                    newElements.add(new Cake(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
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

    public String saveToFile(){

        this.repo.writeToFile(this.repo.getElements());

        return "saved ok";
    }

    public String viewWithCond(String typeOfServing, int price){
        String result="\n";

        for (Goodies goodie : this.repo.getElements()){
            if ((goodie.typeOfServing == typeOfServing) && (goodie.price < price)){
                result += goodie.toString() + "\n";
            }
        }

        //System.out.println("this is the controller" + result);

        return result;
    }

}

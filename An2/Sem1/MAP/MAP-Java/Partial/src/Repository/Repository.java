package Repository;

import Model.Pom;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/16/13
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Repository<T> implements RepoInterface<T> {

    private ArrayList<T> elements;

    @Override
    public ArrayList<T> getElements() {
        return elements;
    }

    @Override
    public void writeToFile(ArrayList<T> elementsToWrite) {
        try{
            FileWriter out = new FileWriter("output.txt");
            for (Object elem : elementsToWrite){
                out.write(elem.toString());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replaceContent(ArrayList<T> content) {
        elements = content;
    }

    @Override
    public void serializeToFile(String fileName){
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.elements);
            out.close();
            fileOut.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deserializeFromFile(String fileName){
        try{
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.elements = (ArrayList<T>) in.readObject();
            in.close();
            fileIn.close();
        }catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

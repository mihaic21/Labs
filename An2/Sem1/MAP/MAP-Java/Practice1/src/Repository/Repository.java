package Repository;

import Model.*;
import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/15/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Repository<T> {

    private ArrayList<T> elements;

    public ArrayList<T> getElements(){
        return elements;
    }

    public void replaceContent(ArrayList<T> content){
        elements = content;
    }

    public void writeToFile(ArrayList<T> elementsToWrite) throws MyException {
        try{
            FileWriter out = new FileWriter("output.txt");
            String className;
            for (T elem : elementsToWrite){
                className = elem.getClass().toString().split("\\.")[1];
                out.write(className);
                out.write(" ");
                out.write(elem.toString());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            throw new MyException("Error writing to file!");
        }
    }

}

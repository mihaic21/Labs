package Repository;

import Model.HasID;
import Model.Comparable;
import Utils.MyException;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/4/13
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Repository<T extends HasID> {

    private Map<Integer, T> elements = new LinkedHashMap<Integer, T>();

    public Repository(Map<Integer, T> map){
        elements = map;
    }

    public Repository(){

    }

    public void addElement(T element){
        elements.put(element.getID(), element);
    }

    public void removeElement(T element){
        elements.remove(element.getID());
    }

    public T getTopElement(){
        for (T elem : elements.values())
            return elem;
        return null;
    }

    public int getNoOfElements(){
        return elements.size();
    }

    public Map<Integer, T> getAllElements(){
        Map<Integer, T> temp = new LinkedHashMap<Integer, T>();
        temp.putAll(elements);
        return temp;
    }

    public void writeToFile(String fileName) throws MyException {
        try {
            FileWriter out = new FileWriter(fileName);
            //Map<Integer, T> temp = getAllElements();
            String className;
            for (T elem : elements.values()){
                className = elem.getClass().toString().split("\\.")[1];
                out.write(className);
                out.write(" ");
                out.write(elem.toString());
                out.write("\n");
            }
            out.close();
        } catch (IOException ex){
            throw new MyException("Error writing to file!");
        }
    }

    public void serializeDataToFile(String fileName){
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

    public void deserializeDataFromFile(String fileName){
        try{
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Map<Integer, T> map = (Map<Integer, T>) in.readObject();
            this.elements = map;
            in.close();
            fileIn.close();
        }catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static<T extends Comparable<T>> int noOfElementsGreaterThan(Map<Integer,T> map, T elem){
        Map<Integer, T> temp = null;
        temp.putAll(map);
        int result = 0;

        for (T comparableElem : temp.values())
            if (comparableElem.isGreaterThan(elem))
                result++;

        return result;
    }

}

package Repository;

import Model.Student;
import Utils.MyException;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/28/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Repository<T> {

    private Stack<T> elements = new Stack<T>();

    public Repository(Stack<T> stack) {
        elements = stack;
    }

    public Repository() {
    }


    public void addElement(T element){
        elements.push(element);
    }

    public void removeElement(T element) throws MyException {
        Stack<T> temp = new Stack<T>();
        while (true) {
            T topElement = this.elements.pop();
            if (element.equals(topElement)){
                break;
            }
            temp.push(topElement);
        }

        while (temp.getStackSize() != 0)
            elements.push(temp.pop());
    }


    public T getTopElement() throws MyException{
        T temp = elements.pop();
        elements.push(temp);
        return temp;
    }

    public int getNoOfElements(){
        return elements.getStackSize();
    }

    public Stack<T> getAllElements(){
        return elements.copy();
    }

    public void writeToFile(String fileName) throws MyException {
        try{
            FileWriter out = new FileWriter(fileName);
            Stack<T> temp = this.getAllElements();
            while (!temp.isEmpty()){
                T topElement = temp.pop();
                String className = topElement.getClass().toString().split("\\.")[1];
                out.write(className+" "+topElement.toString()+"\n");
            }
            out.close();
        }catch (IOException ex){
            throw new MyException("Problems in writing to file!");
        }
    }

    public void replaceContent(Stack<T> stack) throws MyException {
        while (!elements.isEmpty())
            elements.pop();
        while (!stack.isEmpty())
            elements.push(stack.pop());
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
            Stack<T> stack = (Stack<T>) in.readObject();
            this.elements = stack;
            in.close();
            fileIn.close();
        }catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
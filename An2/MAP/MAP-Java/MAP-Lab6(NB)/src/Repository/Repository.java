/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;


import Utils.MyException;

/**
 *
 * @author mihai
 */
public class Repository<T> {
    private Stack<T> elements = new Stack<T>();
    
    public void addElement(T element){
        elements.push(element);
    }
    
    public void removeElement(T element) throws MyException{
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
    
}

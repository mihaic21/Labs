package Repository;

import Utils.MyException;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/27/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Repository<T> {

    private Stack<T> elements = new Stack<T>();

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

}

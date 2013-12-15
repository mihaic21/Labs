/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.LinkedList;

/**
 *
 * @author mihai
 */
public class Node<T> {
    
    public T data;
    public Node<T> next;
    
    public Node(T data){
        this.data = data;
    }
}

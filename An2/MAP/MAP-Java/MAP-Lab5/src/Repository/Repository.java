/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;


import Controller.StudentException;
import Domain.Student;

/**
 *
 * @author mihai
 */
public class Repository {
    private int capacity;
    private Stack stack;
    
    public Repository(int capacity){
        this.capacity = capacity;
        this.stack = new Stack(capacity);
    }
    
    public void addStudent(Student s){
        stack.push(s);
    }
    
    public Student getStudent() throws StudentException{
        return stack.pop();
    }
    
    public int getStackSize(){
        return this.stack.getStackSize();
    }
    
    public int getStackCapacity(){
        return this.stack.getStackCapacity();
    }
}

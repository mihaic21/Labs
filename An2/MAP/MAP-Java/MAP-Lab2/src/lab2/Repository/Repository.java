/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.Repository;

import lab2.Domain.Student;

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
        /*
         * pre cond: s of type Student
         * post cond: -
         * 
         * Adds on top of the stack a student object
         */
        stack.push(s);
    }
    
    public Student getStudent(){
        /*
         * pre cond: -
         * post cond: a Student type object
         * 
         * Removes the element on top of the stack and returns it
         */
        return stack.pop();
    }
    
    public int getStackSize(){
        /*
         * pre cond: -
         * post cond: int - size of stack
         * 
         * Returns the size of the stack (last position used by the stack)
         */
        return this.stack.getStackSize();
    }
    
    public int getStackCapacity(){
        /*
         * pre cond: -
         * post cond: int - max capacity of the stack
         * 
         * Returns the maximum number of elements which can be added in the stack
         */
        return this.stack.getStackCapacity();
    }
}

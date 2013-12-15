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
public class Stack {
    private int size;
    private int capacity;
    private Student[] students;
    
    public Stack(int sCapacity){
        this.size = -1;
        this.capacity = sCapacity;
        this.students = new Student[sCapacity];
    }
    

    
    public void push(Student s){
        size++;
        if (size == capacity){
            this.increaseCapacity();
        }
        students[size] = s;
    }
    
    public Student pop() throws StudentException{
        if (this.isEmpty())
            throw new StudentException("Cannot pop elem from empty stack!");
        else {
            Student student = this.students[size];
            this.students[size] = null;
            this.size--;
            return student;
        }
    }
    
    private void increaseCapacity() {
        capacity = capacity*2;
    }
   
    public int getStackSize(){
        return this.size;
    }
    
    public int getStackCapacity(){
        return this.capacity;
    }
    
    private boolean isEmpty(){
        return this.size < 0;
    }

}

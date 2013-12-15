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
public class Stack {
    private int size, capacity;
    private Student[] students;
    
    public Stack(int sCapacity){
        /*
         * pre cond: int sCapacity - maximum number of students to be added
         * post cond: object Stack
         * 
         * creates Stack, allocates memory
         */
        students = new Student[sCapacity];
        size = -1;
        capacity = sCapacity;
    }
    
    public void push(Student s){
        /*
         * pre cond: s of type Student
         * post cond: -
         * 
         * adds object on top of stack; if maximum capacity is reached,
         * it doubles it
         */
        size++;
        if (size == capacity){
            increaseCapacity();
        }
        students[size] = s;
    }
    
    public Student pop() {
        /*
         * pre cond: -
         * post cond: the last added Student
         * 
         * removes the object on top of the stack and returns it
         */
        if (!isEmpty()) {
            Student student = students[size];
            students[size] = null;
            size--;
            return student;
        }
        return null;
    }
    
    private void increaseCapacity() {
        /*
         * pre cond: -
         * post cond: the capacity is doubled
         * 
         * doubles the current capacity of the stack
         */
        capacity = capacity*2;
    }
    
    public int getStackSize(){
        return this.size;
    }
    
    public int getStackCapacity(){
        return this.capacity;
    }
    
    private boolean isEmpty(){
        if (size < 0) return true;
        return false;
    }
}

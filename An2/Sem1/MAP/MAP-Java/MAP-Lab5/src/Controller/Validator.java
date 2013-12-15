/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Repository.*;
import Domain.Student;

/**
 *
 * @author mihai
 */
public class Validator {
    private Repository repo;
    
    public Validator(Repository repo){
        /*
         * Constructor
         * uses repository
         */
        this.repo = repo;
    }
    
    private boolean studentExists(int id) throws StudentException{
        /*
         * pre cond: int id
         * post cond: true - if student id is found in stack
         *            false - otherwise
         * 
         * checks if a student id was already used
         */
        Student student;
        Stack newStack = new Stack(this.repo.getStackCapacity());
        boolean ok = false;
        while (!ok && this.repo.getStackSize() >-1){
            student = this.repo.getStudent();
            if (student.getId() == id){
                ok = true;
            }
            newStack.push(student);
        }
        while (newStack.getStackSize() > -1){
            this.repo.addStudent(newStack.pop());
        }
        if (ok) return true;
        else return false;
    }
    
    private boolean nameIsEmpty(String name){
        if (name.length() == 0) return true;
        return false;
    }
    
    public void validateStudent(Student s) throws StudentException{
        if (this.nameIsEmpty(s.getName()))
            throw new StudentException("Name cannot be empty!");
        if (this.studentExists(s.getId()))
            throw new StudentException("Student id already exists!");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.Controller;

import lab2.Repository.*;
import lab2.Domain.Student;

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
    
    public boolean studentExists(int id){
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
    
    public boolean nameIsEmpty(String name){
        if (name.length() == 0) return true;
        return false;
    }
}

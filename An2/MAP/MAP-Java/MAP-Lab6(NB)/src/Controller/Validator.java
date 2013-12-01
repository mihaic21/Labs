/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.MyException;
import Repository.*;
import Model.*;

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
    
    private boolean studentExists(int id) throws MyException{

        Stack<Student> students = repo.getAllElements();
        while (!students.isEmpty()){
            Student currStudent;
            currStudent = students.pop();
            if (currStudent.getID() == id)
                return true;
        }
        return false;
    }
    
    private boolean nameIsEmpty(String name){
        if (name.length() == 0) return true;
        return false;
    }
    
    public void validateStudent(Student s) throws MyException{
        if (this.nameIsEmpty(s.getName()))
            throw new MyException("Name cannot be empty!");
        if (this.studentExists(s.getID()))
            throw new MyException("Student id already exists!");
    }
}

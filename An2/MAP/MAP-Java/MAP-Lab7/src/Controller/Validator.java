package Controller;

import Model.Student;
import Repository.Repository;
import Repository.Stack;
import Utils.MyException;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/28/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Validator {
    private Repository repo;

    public Validator(Repository repo){
        this.repo = repo;
    }

    private boolean studentExists(int id) throws MyException {

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

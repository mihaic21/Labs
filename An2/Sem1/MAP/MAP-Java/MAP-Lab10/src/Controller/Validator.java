package Controller;

import Model.Student;
import Repository.Repository;
import Utils.MyException;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/5/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Validator {

    private Repository repo;

    public Validator(Repository repo){
        this.repo = repo;
    }

    private boolean studentsExists(int id){
        return repo.getAllElements().containsKey(id);
    }

    private boolean nameIsEmpty(String name){
        return name.length() == 0;
    }

    public void validateStudent(Student s) throws MyException {
        if (nameIsEmpty(s.getName()))
            throw new MyException("Name cannot be empty!");
        if (studentsExists(s.getID()))
            throw new MyException("Student id already exists!");
    }

}

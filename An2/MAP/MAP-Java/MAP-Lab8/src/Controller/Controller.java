package Controller;

import Model.*;
import Repository.Repository;
import Utils.MyException;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/5/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    private Repository repo;
    private Validator val;

    public Controller(Repository repo, Validator val){
        this.repo = repo;
        this.val = val;
    }

    public String addStudent(int id, String name, int grade){
        try{
            Student s = new Student(id, name, grade);
            val.validateStudent(s);
            repo.addElement(s);
            return "Student added successfully!";
        } catch (MyException ex){
            return ex.getMessage();
        }

    }

    public String addStudent(int id, String name, int grade, int grade2){
        try{
            Student s = new UndergraduateStudent(id, name, grade, grade2);
            val.validateStudent(s);
            repo.addElement(s);
            return "Student added successfully!";
        }catch(MyException ex){
            return ex.getMessage();
        }
    }

    public String addStudent(int id, String name, int grade, String supervisor, int grade2, int grade3){
        try{
            Student s = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
            val.validateStudent(s);
            repo.addElement(s);
            return "Student added successfully!";
        }catch(MyException ex){
            return ex.getMessage();
        }
    }

    public String addStudent(int id, String name, int grade, String supervisor, String thesis, int grade2){
        try{
            Student s = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
            val.validateStudent(s);
            repo.addElement(s);
            return "Student added successfully!";
        }catch(MyException ex){
            return ex.getMessage();
        }
    }

    public int getNoOfStudents(){
        return repo.getNoOfElements();
    }

    public String getAllStudents(){
        return elementsFromMap(repo.getAllElements());
    }

    public static<T> String elementsFromMap(Map<Integer, T> map){
        String result = "\n";
        for (T elem : map.values()){
            result += elem.toString();
            result += "\n";
        }
        return result;
    }

    public String deleteStudentsUntilGrade(int grade){
        Student currStudent = (Student) repo.getTopElement();
        while (currStudent.average() != grade){
            repo.removeElement(currStudent);
            currStudent = (Student) repo.getTopElement();
        }

        while (repo.getNoOfElements() > 0){
            currStudent = (Student) repo.getTopElement();
            if (currStudent.average() == grade)
                return "Students deleted successfully!";
            else {
                repo.removeElement(currStudent);
                currStudent = (Student) repo.getTopElement();
            }
        }

        return "No student with grade 10 found; all students deleted";
    }

    public static<T> void moveElements(Map<Integer, ? extends HasID> source, Map<Integer, T> destination){
        for (HasID elem : source.values()){
            T element = (T) elem;
            destination.put(elem.getID(), element);
        }
    }

}

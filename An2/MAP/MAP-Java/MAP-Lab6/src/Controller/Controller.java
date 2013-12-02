package Controller;

import Model.Comparable;
import Model.Student;
import Model.UndergraduateStudent;
import Model.GraduateStudent;
import Model.PhDStudent;
import Repository.Repository;
import Repository.Stack;
import Utils.MyException;



/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/27/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private Repository repo;
    private Validator val;

    public Controller(Repository<Student> repo, Validator val){

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

    public <T> String getAllStudents(){
        return Controller.elementsFromStack(this.repo.getAllElements());
    }

    public String deleteStudentsUntilGrade(int grade){

        try{
            Student student;
            while (this.repo.getNoOfElements() > 0){
                student = (Student) this.repo.getTopElement();
                if (student.average() == grade)
                    return "Students deleted successfully!";
                else
                    this.repo.removeElement(student);
            }
            return "No students with the grade 10 found; all students have been deleted!";
        } catch (MyException ex){
            return ex.getMessage();
        }
    }

    public int getNoOfStudents(){

        return this.repo.getNoOfElements();
    }

    public static<T> String elementsFromStack(Stack<T> stack){
        Stack<T> copy = stack.copy();
        String result = "\n";

        while (!copy.isEmpty())
            try{
                T element = copy.pop();
                result += element.toString();
                result += "\n";
            }catch (MyException ex){
                return ex.getMessage();
            }

        return result;
    }


    public static String moveElements(Stack<? extends Student> source, Stack<? super Student> destination) {
        Stack<Student> temp = new Stack<Student>();

        try{
            while (!source.isEmpty())
                temp.push(source.pop());
            while (!temp.isEmpty())
                destination.push(temp.pop());
        }catch (MyException ex){
            return ex.getMessage();
        }

        return "Elements moved successfully";
    }
}

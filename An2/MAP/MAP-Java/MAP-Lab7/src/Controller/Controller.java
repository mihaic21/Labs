package Controller;

import Model.GraduateStudent;
import Model.Student;
import Model.PhDStudent;
import Model.UndergraduateStudent;
import Repository.*;
import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/28/13
 * Time: 3:43 PM
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

    public int numberOfStudentGreaterThan(Student student) {
        Stack<Student> allStudents = this.repo.getAllElements();
        int number = 0;

        while (!allStudents.isEmpty()) {
            try {
                Model.Comparable<Student> comparableStudent = allStudents.pop();
                if (comparableStudent.isGreaterThan(student))
                    number++;
            } catch (MyException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return number;
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

    public String saveStudentsInFile(String fileName){
        try{
            repo.writeToFile(fileName);
        }catch (MyException ex){
            return ex.getMessage();
        }
        return "Students saved in file successfully!";
    }

    public String readFromFile(String fileName) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Stack<Student> newStack = new Stack<Student>();
            String line;
            String[] tokens;

            while ((line = br.readLine()) != null){
                tokens = line.split(" ");
                if ("Student".equals(tokens[0]))
                    newStack.push(new Student(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3])));
                else if ("UndergraduateStudent".equals(tokens[0]))
                    newStack.push(new UndergraduateStudent(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4])));
                else if ("GraduateStudent".equals(tokens[0]))
                    newStack.push(new GraduateStudent(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]),tokens[4],Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6])));
                else if ("PhDStudent".equals(tokens[0]))
                    newStack.push(new PhDStudent(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]),tokens[4],tokens[5],Integer.parseInt(tokens[6])));
                else
                    throw new MyException("Error reading from file");
            }

            repo.replaceContent(newStack);

        }catch (IOException ex){
            return ex.getMessage();
        }catch (MyException ex){
            return ex.getMessage();
        }catch (NumberFormatException ex){
            return "Invalid format in file";
        }
        return "Students read from file successfully!";
    }

}

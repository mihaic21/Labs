package Controller;

import Model.*;
import Repository.Repository;
import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
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
        Student currStudent;
        while ((currStudent = (Student) repo.getTopElement()) != null){
            if (!(currStudent.average() == grade))
                repo.removeElement(currStudent);
            else
                return "Students deleted successfully!";
        }

        return "No student with grade 10 found; all students deleted";
    }

    public static<T> void moveElements(Map<Integer, ? extends HasID> source, Map<Integer, T> destination){
        for (HasID elem : source.values()){
            T element = (T) elem;
            destination.put(elem.getID(), element);
        }
    }

    public String saveStudentsInFile(String fileName){
        try{
            repo.writeToFile(fileName);
            return "Students saved in file successfully!";
        } catch (MyException ex){
            return ex.getMessage();
        }
    }

    public String readFromFile(String fileName){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Map<Integer, Student> map = new LinkedHashMap<Integer, Student>();
            String line;

            while ((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if ("Student".equals(tokens[0]))
                    map.put(Integer.parseInt(tokens[1]), new Student(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3])));
                else if ("UndergraduateStudent".equals(tokens[0]))
                    map.put(Integer.parseInt(tokens[1]), new UndergraduateStudent(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4])));
                else if ("GraduateStudent".equals(tokens[0]))
                    map.put(Integer.parseInt(tokens[1]), new GraduateStudent(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]),tokens[4],Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6])));
                else if ("PhDStudent".equals(tokens[0]))
                    map.put(Integer.parseInt(tokens[1]), new PhDStudent(Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]),tokens[4],tokens[5],Integer.parseInt(tokens[6])));
                else
                    throw new MyException("Error reading from file!");
            }

            this.repo = new Repository(map);
            this.val = new Validator(this.repo);

            return "Students read from file successfully!";

        }catch (IOException e){
            return e.getMessage();
        } catch (MyException e) {
            return e.getMessage();
        } catch (NumberFormatException e){
            return "Invalid format exception!";
        }
    }

}

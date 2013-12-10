package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/4/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Student implements Comparable<Student>, HasID {

    private int id;
    private String name;
    private int grade;

    public Student (int id, String name, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public float average(){
        return grade;
    }

    public int getID() {
        return this.id;
    }

    public int getGrade() {
        return this.grade;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + grade;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        return grade > student.grade;
    }

}

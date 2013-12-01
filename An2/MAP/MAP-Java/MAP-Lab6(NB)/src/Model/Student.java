/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mihai
 */
public class Student implements Comparable<Student> {
    private String name;
    private int id;
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

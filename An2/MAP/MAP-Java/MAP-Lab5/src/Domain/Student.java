/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author mihai
 */
public class Student {
    private String name;
    private int id;
    private int grade;
    
    Student() {
        name = null;
        id = 0;
        grade = 0;
    }
    
    public Student (String name, int id, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }
    
    public int getId() {
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
        return name + " " + id + " " + grade;
    }
}

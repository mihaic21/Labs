/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mihai
 */
public class PhDStudent extends Student {
    
    private String supervisor;
    private String thesis;
    private int grade2;
    
    public PhDStudent(int id, String name, int grade, String supervisor, String thesis, int grade2){
        super(id, name, grade);
        this.supervisor = supervisor;
        this.thesis = thesis;
        this.grade2 = grade2;
    }
    
    @Override
    public float average(){
        return (this.getGrade() + grade2) / 2;
    }
    
    @Override
    public boolean isGreaterThan(Student student){
        return (this.average() > student.average());
    }
    
    @Override
    public String toString(){
        return this.getID()+" "+this.getName()+" "+this.getGrade()+" "+supervisor+" "+thesis+" "+grade2;
    }
    
}

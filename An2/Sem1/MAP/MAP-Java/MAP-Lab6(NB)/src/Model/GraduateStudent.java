/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mihai
 */
public class GraduateStudent extends Student {
    
    private String supervisor;
    private int grade2;
    private int grade3;
    
    public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3){
        super(id, name, grade);
        this.supervisor = supervisor;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }
    
    @Override
    public float average(){
        return (this.getGrade() + grade2 + grade3) / 3;
    }
    
    @Override
    public boolean isGreaterThan(Student student){
        return (this.average() > student.average());
    }
    
    @Override
    public String toString(){
        return this.getID()+" "+this.getName()+" "+this.getGrade()+" "+supervisor+" "+grade2+" "+grade3;
    }
    
}

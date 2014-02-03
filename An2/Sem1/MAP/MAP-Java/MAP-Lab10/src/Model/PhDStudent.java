package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/4/13
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhDStudent extends Student {

    public String supervisor;
    public String thesis;
    public int grade2;

    public PhDStudent(){}

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

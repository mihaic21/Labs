package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/28/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UndergraduateStudent extends Student {
    private int grade2;

    public UndergraduateStudent(int id, String name, int grade, int grade2){
        super(id, name, grade);
        this.grade2 = grade2;
    }

    @Override
    public float average(){
        return (this.getGrade() + this.grade2) / 2;
    }

    @Override
    public boolean isGreaterThan(Student student){
        return (this.average() > student.average());
    }

    @Override
    public String toString(){
        return this.getID() + " " + getName() + " " + this.getGrade() + " " + grade2;
    }
}

package UI;

import Model.Student;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mihai on 1/13/14.
 */
public class StudentsObserver implements Observer {

    private String comparator;

    public StudentsObserver(String comparator){
        this.comparator = comparator;
    }

    @Override
    public void update(Observable o, Object arg) {
        Map<Integer, Student> map = (Map<Integer, Student>) arg;
        for (Student student : map.values()) {
            if ("<".equals(this.comparator)) {
                if (student.average() < 5) {
                    System.out.println(student.toString() + "< 5");
                }
            } else {
                if (student.average() >= 5) {
                    System.out.println(student.toString() + ">= 5");
                }
            }
        }
    }
}

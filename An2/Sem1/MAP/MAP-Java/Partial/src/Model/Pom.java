package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/16/13
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pom {

    private String name;
    private int age;

    public Pom(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getMyClass(){
        return this.getClass().toString().split("\\.")[1];
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        //String className = this.getClass().toString().split("\\.")[1];
        return this.getMyClass()+" "+name+" "+age;
    }

}

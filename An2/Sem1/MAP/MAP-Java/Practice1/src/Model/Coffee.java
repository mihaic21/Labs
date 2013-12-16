package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/15/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coffee implements Beverage {

    public String name;

    public Coffee(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean canBeDecoratedWithWhippedCream() {
        return true;
    }
}

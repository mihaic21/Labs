package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/15/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Juice implements Beverage {

    public String name;

    public Juice(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean canBeDecoratedWithWhippedCream() {
        return false;
    }
}

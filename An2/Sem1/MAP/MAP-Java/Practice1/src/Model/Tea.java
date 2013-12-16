package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/15/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tea implements Beverage {

    public String name;

    public Tea(String name){
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

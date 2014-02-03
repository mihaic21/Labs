package Model;

/**
 * Created by mihai on 2/3/14.
 */
public class Coffee extends Goodies {

    public String typeOfCoffee;

    public Coffee(){}

    public Coffee(String typeOfCoffee, String typeOfServing, int price){
        super(typeOfServing, price);
        this.typeOfCoffee = typeOfCoffee;
    }

    public String toString(){
        return this.getMyClass() + " " + typeOfCoffee + " " + typeOfServing + " " + price;
    }

}

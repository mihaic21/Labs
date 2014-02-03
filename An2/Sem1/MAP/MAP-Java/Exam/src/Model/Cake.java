package Model;

/**
 * Created by mihai on 2/3/14.
 */
public class Cake extends Goodies {

    public String shape;
    public String typeOfCake;

    public Cake(){}

    public Cake(String shape, String typeOfCake, String typeOfServing, int price){
        super(typeOfServing,price);
        this.shape = shape;
        this.typeOfCake = typeOfCake;
    }

    public Cake(String shape, String typeOfCake, int price){
        super("cold",price);
        this.shape = shape;
        this.typeOfCake = typeOfCake;
    }

    public String toString(){
        return this.getMyClass() + " " + shape + " " + typeOfCake + " " + price;
    }

}

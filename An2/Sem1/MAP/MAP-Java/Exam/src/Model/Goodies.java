package Model;

/**
 * Created by mihai on 2/3/14.
 */
public class Goodies {

    public String typeOfServing;
    public int price;

    public Goodies(){}

    public Goodies(String typeOfServing, int price){
        this.typeOfServing = typeOfServing;
        this.price = price;
    }

    public String getMyClass(){
        return this.getClass().toString().split("\\.")[1];
    }

    @Override
    public String toString(){
        return this.getMyClass() + " " + this.typeOfServing + " " + this.price;
    }

}

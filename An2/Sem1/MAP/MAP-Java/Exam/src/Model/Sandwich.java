package Model;

/**
 * Created by mihai on 2/3/14.
 */
public class Sandwich extends Goodies {

    public int weight;
    public String content;

    public Sandwich(){}

    public Sandwich(int weight, String content, String typeOfServing, int price){
        super(typeOfServing, price);
        this.weight = weight;
        this.content = content;
    }

    public String toString(){
        return this.getMyClass() + " " + weight + " " + content + " " + typeOfServing + " " + price;
    }

}

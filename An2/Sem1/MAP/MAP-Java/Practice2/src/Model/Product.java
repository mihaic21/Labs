package Model;


/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/15/13
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Product {

    private String name;
    private int expirationDate;

    public Product(String name, int expirationDate){
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public int getExpirationDate(){
        return this.expirationDate;
    }

    @Override
    public String toString(){
        return name +" "+ expirationDate;
    }

}

package Model;

import java.util.logging.Logger;

/**
 * Created by mihai on 4/24/14.
 */
public class Product {

    private String code;
    private String name;
    private int quantity;
    private int price;

    public Product(String code, String name, int quantity, int price) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
        this.code = null;
        this.name = null;
        this.quantity = 0;
        this.price = 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

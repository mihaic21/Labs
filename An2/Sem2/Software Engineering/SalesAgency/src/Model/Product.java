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
    private static Logger logger = Logger.getLogger("Products");

    public Product(String code, String name, int quantity, int price) {
        logger.info("[Entering:] Product.init");
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
        logger.info("[Entering:] Product.init");
        this.code = null;
        this.name = null;
        this.quantity = 0;
        this.price = 0;
    }


    //Getters and Setters

    public String getCode() {
        logger.info("[Entering:] Product.init");
        return code;
    }

    public void setCode(String code) {
        logger.info("[Entering:] Product.setCode");
        this.code = code;
    }

    public String getName() {
        logger.info("[Entering:] Product.getName");
        return name;
    }

    public void setName(String name) {
        logger.info("[Entering:] Product.setName");
        this.name = name;
    }

    public int getQuantity() {
        logger.info("[Entering:] Product.getQuantity");
        return quantity;
    }

    public void setQuantity(int quantity) {
        logger.info("[Entering:] Product.setQuantity");
        this.quantity = quantity;
    }

    public int getPrice() {
        logger.info("[Entering:] Product.getPrice");
        return price;
    }

    public void setPrice(int price) {
        logger.info("[Entering:] Product.setPrice");
        this.price = price;
    }

}

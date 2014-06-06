package Model;

/**
 * Created by mihai on 28.05.2014.
 */
public class Order {

    private int code;
    private Product product;
    private Client client;
    private int quantity;

    public Order(int code, Product product, Client client, int quantity) {
        this.code = code;
        this.product = product;
        this.client = client;
        this.quantity = quantity;
    }

    public int getOrderTotal(){
        return this.product.getPrice() * this.quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

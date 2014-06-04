package Controller;

import Model.Product;

import java.util.List;

/**
 * Created by mihai on 02.06.2014.
 */
public interface ControllerInterface {

    public List<Product> getAllProducts();
    public List<Product> getProductsContaining(String name);
    public void orderProduct(String code, int newQuantity) throws Exception;

}

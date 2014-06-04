package Repository;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 4/26/14.
 */
public interface Repository<Product> {

    public List<Product> getAll();
    public Product getProductByID(String id);
    public void loadProducts();
    public List<Product> getProductsContaining(String name);
    public void updateProduct(String id, int quantity);

}

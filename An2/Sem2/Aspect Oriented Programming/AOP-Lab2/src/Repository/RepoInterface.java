package Repository;

import Model.Product;

import java.util.ArrayList;

/**
 * Created by mihai on 4/26/14.
 */
public interface RepoInterface<Product> {

    public ArrayList<Product> getAll();
    public Product getProductByID(String id);
    public void loadProducts();
    public ArrayList<Product> getProductsContaining(String name);
    public void updateProduct(String id, int quantity);

}

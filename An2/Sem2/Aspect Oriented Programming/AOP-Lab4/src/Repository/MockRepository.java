package Repository;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 02.06.2014.
 */
public class MockRepository implements Repository {

    private ArrayList<Product> products = new ArrayList<Product>();

    public MockRepository() {

        products.add(new Product("122456", "Produs", 730, 100));
        products.add(new Product("125446", "Asus Taichi", 123, 4000));
        products.add(new Product("748391", "Macbook Pro", 358, 3943));
        products.add(new Product("859353", "Lenovo", 330, 1100));
        products.add(new Product("849204", "Laptop", 34, 1300));

    }

    @Override
    public List getAll() {
        return this.products;
    }

    @Override
    public Object getProductByID(String id) {
        return null;
    }

    @Override
    public void loadProducts() {

    }

    @Override
    public List getProductsContaining(String name) {
        return null;
    }

    @Override
    public void updateProduct(String id, int quantity) {

    }
}

package Repository;

import Model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 26.05.2014.
 */
public class RepositoryDAO extends JdbcDaoSupport implements Repository {

    private List<Product> products = new ArrayList<Product>();

    public RepositoryDAO() {

        //this.loadProducts();

    }

    @Override
    public List getAll() {
        this.loadProducts();
        return this.products;
    }

    @Override
    public Product getProductByID(String id) {
        Product product = getJdbcTemplate().queryForObject("SELECT * from Products where productID=?", new PartMapper(), id);
        return product;
    }

    @Override
    public void loadProducts() {
        this.products = getJdbcTemplate().query("select * from Products", new PartMapper());
    }

    @Override
    public List getProductsContaining(String name) {
        return null;
    }

    @Override
    public void updateProduct(String id, int quantity) {
        this.getProductByID(id).setQuantity(quantity);

        getJdbcTemplate().update("update Products set productQuantity = ? where productID = ?", quantity, id);
        this.loadProducts();
    }

    private static class PartMapper implements RowMapper<Model.Product>{
        @Override
        public Model.Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product(resultSet.getString("productID"), resultSet.getString("productName"),
                    resultSet.getInt("productQuantity"), resultSet.getInt("productPrice"));
            return product;
        }
    }

}

package Repository;

import Model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by mihai on 4/26/14.
 */
public class RepositoryDB implements Repository {

    private String tableName;
    private ArrayList<Product> products = new ArrayList<Product>();
    private DBConnection dbConnection;

    public RepositoryDB(String tableName) {

        this.tableName = tableName;
        this.dbConnection = new DBConnection();
        this.loadProducts();

    }

    @Override
    public ArrayList<Product> getAll() {
        return this.products;
    }

    @Override
    public Product getProductByID(String id) {
        for (Product product : products){
            if (product.getCode() == id){
                return product;
            }
        }

        return null;
    }

    @Override
    public void loadProducts() {
        try {
            Connection connection = this.dbConnection.getConnection();
            ResultSet resultSet = DBConnection.getTable(connection, tableName);
            while (resultSet.next()){

                Product product = new Product();
                product.setCode(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setQuantity(Integer.parseInt(resultSet.getString(3)));
                product.setPrice(Integer.parseInt(resultSet.getString(4)));

                this.products.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Product> getProductsContaining(String name) {
        String query = "SELECT * FROM Products WHERE productName like '%" + name + "%'";
        ArrayList<Product> resultList = new ArrayList<Product>();

        try {
            Statement statement = null;
            Connection connection = this.dbConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                Product product = new Product();
                product.setCode(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setQuantity(Integer.parseInt(resultSet.getString(3)));
                product.setPrice(Integer.parseInt(resultSet.getString(4)));

                resultList.add(product);
            }

            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateProduct(String id, int quantity) {
        String query = "UPDATE " + tableName + "\nSET productQuantity=" + quantity + "\nWHERE productID=" + id;

        try {
            Statement statement = null;
            Connection connection = this.dbConnection.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            this.getProductByID(id).setQuantity(quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

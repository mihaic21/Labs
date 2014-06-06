package Repository;

import Model.*;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by mihai on 4/26/14.
 */
public class RepositoryDB implements RepoInterface {

    private String productsTableName;
    private String administratorsTableName;
    private String salesmenTableName;
    private String clientsTableName;
    private String ordersTableName;

    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Administrator> administrators = new ArrayList<Administrator>();
    private ArrayList<Salesman> salesmen = new ArrayList<Salesman>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Order> orders = new ArrayList<Order>();

    private DBConnection dbConnection;
    
    private static Logger logger = Logger.getLogger("Products");

    public RepositoryDB(String productsTableName, String administratorsTableName, String salesmenTableName,
                        String clientsTableName, String ordersTableName) {

        logger.info("[Entering:] RepositoryDB.init");

        this.productsTableName = productsTableName;
        this.administratorsTableName = administratorsTableName;
        this.salesmenTableName = salesmenTableName;
        this.clientsTableName = clientsTableName;
        this.ordersTableName = ordersTableName;

        this.dbConnection = new DBConnection();
        this.loadProducts();
        this.loadAdministrators();
        this.loadSalesmen();
        this.loadClients();
        this.loadOrders();

    }

    @Override
    public ArrayList<Product> getAllProducts() {
        logger.info("[Entering:] RepositoryDB.getAllProducts");
        return this.products;
    }

    @Override
    public ArrayList getAllAdministrators() {
        return this.administrators;
    }

    @Override
    public ArrayList getAllSalesmen() {
        return this.salesmen;
    }

    @Override
    public ArrayList getAllClients() {
        return this.clients;
    }

    @Override
    public ArrayList getAllOrders() {
        return this.orders;
    }

    @Override
    public Product getProductByID(String id) {
        logger.info("[Entering:] RepositoryDB.getProductByID");
        for (Product product : products){
            if (product.getCode().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public Client getClientByCNP(String cnp) {
        logger.info("[Entering:] RepositoryDB.getClientByCNP");
        for (Client client : clients){
            if (client.getCnp().equals(cnp)){
                return client;
            }
        }
        return null;
    }

    @Override
    public Order getOrderByID(int id) {
        logger.info("[Entering:] RepositoryDB.getOrderByID");
        for (Order order : orders){
            if (order.getCode() == id){
                return order;
            }
        }
        return null;
    }

    @Override
    public int getTotalPriceSpentByClient(Client client) {

        int total = 0;

        for (Order order : orders){
            if (order.getClient() == client){
                total += order.getOrderTotal();
            }
        }

        return total;
    }

    @Override
    public Administrator getAdministratorByUsername(String username) {
        logger.info("[Entering:] RepositoryDB.getAdministratorByUsername");
        for (Administrator administrator : administrators){
            if (administrator.getUsername().equals(username)){
                return administrator;
            }
        }
        return null;
    }

    @Override
    public Salesman getSalesmanByUsername(String username) {
        logger.info("[Entering:] RepositoryDB.getSalesmanByUsername");
        for (Salesman salesman : salesmen){
            if (salesman.getUsername().equals(username)){
                return salesman;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        logger.info("[Entering:] RepositoryDB.addProduct");

        String query = "insert into " + productsTableName + " (productID, productName, productQuantity, productPrice) " +
                "values ('" + product.getCode() + "','" + product.getName() + "','" +
                product.getQuantity() + "','" + product.getPrice() +"');";
        try{
            Statement statement = null;
            Connection connection = this.dbConnection.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            this.products.add(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addAdministrator(Administrator administrator) {
        logger.info("[Entering:] RepositoryDB.addAdministrator");

        String query = "insert into " + administratorsTableName + " (adminUsername, adminPassword) values " +
                " ('" + administrator.getUsername() + "','" + administrator.getPassword() + "');";
        try{
            Statement statement = null;
            Connection connection = this.dbConnection.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            this.administrators.add(administrator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSalesman(Salesman salesman) {
        logger.info("[Entering:] RepositoryDB.addSalesman");

        String query = "insert into " + salesmenTableName + " (salesmanUsername, salesmanPassword) values " +
                " ('" + salesman.getUsername() + "','" + salesman.getPassword() + "');";
        try {
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.salesmen.add(salesman);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addClient(Client client) {
        logger.info("[Entering:] RepositoryDB.addClient");

        String query = "insert into " + clientsTableName + " values " + " ('" + client.getCnp() + "', '" +
                client.getName() + "', '" + client.getAddress() + "');";
        try{
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.clients.add(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrder(Order order) {
        logger.info("[Entering:] RepositoryDB.addOrder");

        String query = "insert into " + ordersTableName + " values " + " ('" + order.getCode() + "', '" +
                order.getProduct().getCode() + "', '" + order.getClient().getCnp() + "', '" + order.getQuantity() + "');";
        try {
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.orders.add(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(Product product) {
        logger.info("[Entering:] RepositoryDB.removeProduct");
        String query = "delete from " + productsTableName + " where productID='" + product.getCode() + "';";
        try {
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.products.remove(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeAdministrator(Administrator administrator) {
        logger.info("[Entering:] RepositoryDB.removeAdministrator");
        String query = "delete from " + administratorsTableName + " where adminUsername='" +
                administrator.getUsername() + "';";
        try{
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.administrators.remove(administrator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSalesman(Salesman salesman) {
        logger.info("[Entering:] RepositoryDB.removeSalesman");
        String query = "delete from " + salesmenTableName + " where salesmanUsername='" +
                salesman.getUsername() + "';";
        try{
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.salesmen.remove(salesman);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeClient(Client client) {
        logger.info("[Entering:] RepositoryDB.removeClient");
        String query = "delete from " + clientsTableName + " where clientCNP='" + client.getCnp() + "';";
        try {
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.clients.remove(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeOrder(Order order) {
        logger.info("[Entering:] RepositoryDB.removeOrder");
        String query = "delete from " + ordersTableName + " where orderCode='" + order.getCode() + "';";
        try {
            Connection connection = this.dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            this.orders.remove(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadProducts() {

        logger.info("[Entering:] RepositoryDB.loadProducts");
        try {
            Connection connection = this.dbConnection.getConnection();
            ResultSet resultSet = DBConnection.getTable(connection, productsTableName);
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
    public void loadAdministrators() {
        logger.info("[Entering:] RepositoryDB.loadAdministrators");
        try{
            Connection connection = this.dbConnection.getConnection();
            ResultSet resultSet = DBConnection.getTable(connection, administratorsTableName);
            while (resultSet.next()){
                Administrator administrator = new Administrator();
                administrator.setUsername(resultSet.getString(1));
                administrator.setPassword(resultSet.getString(2));

                this.administrators.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadSalesmen() {
        logger.info("[Entering:] RepositoryDB.loadSalesmen");
        try{
            Connection connection = this.dbConnection.getConnection();
            ResultSet resultSet = DBConnection.getTable(connection, salesmenTableName);
            while (resultSet.next()){
                Salesman salesman = new Salesman();
                salesman.setUsername(resultSet.getString(1));
                salesman.setPassword(resultSet.getString(2));

                this.salesmen.add(salesman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadClients() {
        logger.info("[Entering:] RepositoryDB.loadClients");

        try{
            Connection connection = this.dbConnection.getConnection();
            ResultSet resultSet = DBConnection.getTable(connection, clientsTableName);
            while (resultSet.next()){
                Client client = new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                clients.add(client);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadOrders() {
        logger.info("[Entering:] RepositoryDB.loadOrders");

        try{
            Connection connection = this.dbConnection.getConnection();
            ResultSet resultSet = dbConnection.getTable(connection, ordersTableName);
            while (resultSet.next()){
                int code = Integer.parseInt(resultSet.getString(1));
                Product product = this.getProductByID(resultSet.getString(2));
                Client client = this.getClientByCNP(resultSet.getString(3));
                int quantity = Integer.parseInt(resultSet.getString(4));
                Order order = new Order(code, product, client, quantity);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateProduct(String id, int quantity) {
        logger.info("[Entering:] RepositoryDB.updateProduct");
        String query = "UPDATE " + productsTableName + "\nSET productQuantity=" + quantity + "\nWHERE productID=" + id;

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

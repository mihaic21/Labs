package Repository;

import Model.*;

import java.util.ArrayList;

/**
 * Created by mihai on 4/26/14.
 */
public interface RepoInterface {

    public ArrayList getAllProducts();
    public ArrayList getAllAdministrators();
    public ArrayList getAllSalesmen();
    public ArrayList getAllClients();
    public ArrayList getAllOrders();
    public Product getProductByID(String id);
    public Administrator getAdministratorByUsername(String username);
    public Salesman getSalesmanByUsername(String username);
    public Client getClientByCNP(String cnp);
    public Order getOrderByID(int id);
    public int getTotalPriceSpentByClient(Client client);
    public void addProduct(Product product);
    public void addAdministrator(Administrator administrator);
    public void addSalesman(Salesman salesman);
    public void addClient(Client client);
    public void addOrder(Order order);
    public void removeProduct(Product product);
    public void removeAdministrator(Administrator administrator);
    public void removeSalesman(Salesman salesman);
    public void removeClient(Client client);
    public void removeOrder(Order order);
    public void updateProduct(String id, int quantity);
    public void loadProducts();
    public void loadAdministrators();
    public void loadSalesmen();
    public void loadClients();
    public void loadOrders();

}

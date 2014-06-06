package Controller;

import Model.Client;
import Model.Order;
import Model.Product;
import Repository.RepoInterface;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mihai on 23.05.2014.
 */
public class SalesmanController extends Observable {

    private RepoInterface repository;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public SalesmanController(RepoInterface repository) {
        this.repository = repository;
    }

    public ArrayList<Product> getAllProducts(){
        if (this.repository.getAllProducts() == null){
            return null;
        } else {
            return this.repository.getAllProducts();
        }
    }

    public ArrayList<Client> getAllClients(){
        if (this.repository.getAllClients() == null){
            return null;
        } else {
            return this.repository.getAllClients();
        }
    }

    public ArrayList<Order> getAllOrders(){
        if (this.repository.getAllOrders() == null){
            return null;
        } else {
            return this.repository.getAllOrders();
        }
    }

    public int getTotalPriceSpentByClient(Client client){
        return this.repository.getTotalPriceSpentByClient(client);
    }

    public void addClient(Client client){
        this.repository.addClient(client);
        this.notifyObservers(this);
    }

    public void orderProduct(String productCode, int quantity, String clientCNP){

        Product product = this.repository.getProductByID(productCode);
        product.setQuantity(product.getQuantity() - quantity);
        this.repository.updateProduct(productCode, product.getQuantity());

        Client client = this.repository.getClientByCNP(clientCNP);

        int orderCode;
        if (this.repository.getAllOrders().size() < 1){
            orderCode = 0;
        } else {
            orderCode = ((ArrayList< Order>)this.repository.getAllOrders()).get(this.repository.getAllOrders().size()-1).getCode() + 1;
        }
        this.repository.addOrder(new Order(orderCode, product, client, quantity));
        this.notifyObservers(this);

    }

    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Observable observable) {
        ArrayList list = new ArrayList();
        list.add(getAllProducts());
        list.add(getAllClients());
        for (Observer observer : this.observers){
            observer.update(observable, list);
        }
    }
}

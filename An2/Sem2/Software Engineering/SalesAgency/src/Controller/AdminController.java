package Controller;

import Model.*;
import Repository.RepoInterface;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mihai on 23.05.2014.
 */
public class AdminController extends Observable{

    private RepoInterface repository;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public AdminController(RepoInterface repository) {
        this.repository = repository;
    }

    public ArrayList<Product> getAllProducts(){
        return this.repository.getAllProducts();
    }

    public ArrayList<Administrator> getAllAdministrators(){
        return this.repository.getAllAdministrators();
    }

    public ArrayList<Salesman> getAllSalesmen(){
        return this.repository.getAllSalesmen();
    }

    public ArrayList<Client> getAllClients(){
        return this.repository.getAllClients();
    }

    public ArrayList<Order> getAllOrders(){
        return this.repository.getAllOrders();
    }

    public void addItem(Product product){
        this.repository.addProduct(product);
        this.notifyObservers(this);
    }

    public void addItem(Administrator administrator){
        this.repository.addAdministrator(administrator);
        this.notifyObservers(this);
    }

    public void addItem(Salesman salesman){
        this.repository.addSalesman(salesman);
        this.notifyObservers(this);
    }

    public void removeProduct(String code){
        this.repository.removeProduct(this.repository.getProductByID(code));
        ArrayList list = new ArrayList(this.repository.getAllOrders());
        for (Object ord : list){
            Order order = (Order) ord;
            if (order.getProduct().getCode().equals(code)){
                this.repository.removeOrder(order);
            }
        }
        this.notifyObservers(this);
    }

    public void removeAdministrator(String username){
        this.repository.removeAdministrator(this.repository.getAdministratorByUsername(username));
        this.notifyObservers(this);
    }

    public void removeSalesman(String username){
        this.repository.removeSalesman(this.repository.getSalesmanByUsername(username));
        this.notifyObservers(this);
    }

    public void removeClient(String cnp){
        this.repository.removeClient(this.repository.getClientByCNP(cnp));
        ArrayList list = new ArrayList(this.repository.getAllOrders());
        for (Object ord : list){
            Order order = (Order) ord;
            if (order.getClient().getCnp().equals(cnp)){
                this.repository.removeOrder(order);
            }
        }
        this.notifyObservers(this);
    }

    public void removeOrder(int id){
        this.repository.removeOrder(this.repository.getOrderByID(id));
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
        list.add(this.repository.getAllProducts());
        list.add(this.repository.getAllAdministrators());
        list.add(this.repository.getAllSalesmen());
        for (Observer observer : this.observers){
            observer.update(observable, list);
        }
    }

}

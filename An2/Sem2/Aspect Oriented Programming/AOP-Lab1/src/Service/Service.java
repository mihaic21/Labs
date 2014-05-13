package Service;

import Model.Product;
import Repository.RepoInterface;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by mihai on 4/26/14.
 */
public class Service extends Observable{
    private static Logger logger = Logger.getLogger("Products");
    private RepoInterface<Product> repository;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public Service(RepoInterface repository) {
        logger.info("[Entering:]Service.init");
        this.repository = repository;
    }

    public ArrayList<Product> getAllProducts(){
        logger.info("[Entering:]Service.getAllProducts");
        return this.repository.getAll();
    }

    public Product getProductByID(String id){
        logger.info("[Entering:]Service.getProductByID");
        return this.repository.getProductByID(id);
    }

    public ArrayList<Product> getProductsContaining(String name){
        logger.info("[Entering:]Service.getProductsContaining");
        return this.repository.getProductsContaining(name);
    }

    public void updateProductQuantity(String code, int quantity){
        logger.info("[Entering:]Service.updateProductQuantity");
        this.repository.updateProduct(code, this.repository.getProductByID(code).getQuantity() - quantity);
        this.notifyObservers(this, repository.getAll());
    }


    //Observers

    @Override
    public synchronized void addObserver(Observer o) {
        logger.info("[Entering:]Service.addObserver");
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        logger.info("[Entering:]Service.deleteObserver");
        observers.remove(o);
    }

    public void notifyObservers(Observable observable, ArrayList<Product> productArrayList) {
        logger.info("[Entering:]Service.notifyObservers");
        for (Observer observer : this.observers){
            observer.update(observable, productArrayList);
        }
    }

}

package Controller;

import Model.Product;
import Service.Service;
import sun.org.mozilla.javascript.commonjs.module.provider.StrongCachingModuleScriptProvider;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by mihai on 4/26/14.
 */
public class Controller extends Observable implements Observer{

    private Service service;
    private static Logger logger = Logger.getLogger("Products");
    ArrayList<Observer> observers = new ArrayList<Observer>();

    public Controller(Service service) {
        logger.info("[Entering:] Controller.init");
        this.service = service;
        this.service.addObserver(this);
    }

    public ArrayList<Product> getAllProducts(){
        logger.info("[Entering:] Controller.getAllProducts");
        return this.service.getAllProducts();
    }

    public ArrayList<Product> getProductsContaining(String name){
        logger.info("[Entering:] Controller.getProductsContaining");
        return this.service.getProductsContaining(name);
    }

    public void orderProduct(String code, int newQuantity) throws Exception {
        logger.info("[Entering:] Controller.orderProduct");
        if (service.getProductByID(code).getQuantity() < newQuantity){
            throw new Exception("Quantity not available!");
        } else {
            service.updateProductQuantity(code, newQuantity);
        }

    }


    //Observers

    @Override
    public synchronized void addObserver(Observer o) {
        logger.info("[Entering:]Controller.addObserver");
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        logger.info("[Entering:]Controller.deleteObserver");
        observers.remove(o);
    }

    public void notifyObservers(Observable observable, ArrayList<Product> productArrayList) {
        logger.info("[Entering:]Controller.notifyObservers");
        for (Observer observer : this.observers){
            observer.update(observable, productArrayList);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        logger.info("[Entering:] Controller.update");
        ArrayList<Product> productArrayList = (ArrayList<Product>) o;
        this.notifyObservers(this, productArrayList);
    }
}

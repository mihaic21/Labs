package Controller;

import Model.Product;
import Repository.RepoInterface;
import Utils.ChangesSubject;
import sun.org.mozilla.javascript.commonjs.module.provider.StrongCachingModuleScriptProvider;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by mihai on 4/26/14.
 */
public class Controller{

    private RepoInterface<Product> repository;

    public Controller(RepoInterface repository) {
        this.repository = repository;
    }

    public ArrayList<Product> getAllProducts(){
        return this.repository.getAll();
    }

    public ArrayList<Product> getProductsContaining(String name){
        return this.repository.getProductsContaining(name);
    }

    @ChangesSubject
    public void orderProduct(String code, int newQuantity) throws Exception {
        if (repository.getProductByID(code).getQuantity() < newQuantity){
            throw new Exception("Quantity not available!");
        } else {
            repository.updateProduct(code, repository.getProductByID(code).getQuantity() - newQuantity);
        }
    }

}

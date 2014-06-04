package Controller;

import Model.Product;
import Repository.Repository;
import Utils.ChangesSubject;

import java.util.List;

/**
 * Created by mihai on 4/26/14.
 */
public class Controller implements ControllerInterface{

    private Repository<Product> repository;


    public Controller(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts(){
        return this.repository.getAll();
    }

    @Override
    public List<Product> getProductsContaining(String name){
        return this.repository.getProductsContaining(name);
    }

    @Override
    @ChangesSubject
    public void orderProduct(String code, int newQuantity) throws Exception {
        if (repository.getProductByID(code).getQuantity() < newQuantity){
            throw new Exception("Quantity not available!");
        } else {
            repository.updateProduct(code, repository.getProductByID(code).getQuantity() - newQuantity);
        }
    }

}

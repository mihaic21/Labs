package Utils;

import Model.Administrator;
import Model.Client;
import Model.Product;
import Model.Salesman;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mihai on 25.05.2014.
 */
public class Validator {

    public static void validateProduct(Product product, ArrayList<Product> productArrayList) throws MyException {

        if (product.getCode().isEmpty() || product.getName().isEmpty()){
            throw new MyException("Field cannot be empty");
        }

        if (product.getCode().length() != 6){
            throw new MyException("Invalid code");
        }

        if (!product.getName().matches("[a-zA-Z]+")){
            throw new MyException("Invalid name");
        }

        if (product.getName().length() > 15){
            throw new MyException("Name is too long");
        }

        if (product.getCode().length() > 15){
            throw new MyException("Code is too long");
        }

        for (Product otherProduct : productArrayList){
            if (otherProduct.getCode().equals(product.getCode())){
                throw new MyException("Product code already exists");
            }
        }

        if (product.getQuantity() < 0){
            throw new MyException("Quantity cannot be negative");
        }

        if (product.getPrice() < 0){
            throw new MyException("Price cannot be negative");
        }

    }

    public static void validateAdministrator(Administrator administrator, ArrayList<Administrator> administratorArrayList) throws MyException {

        if (administrator.getUsername().isEmpty() || administrator.getPassword().isEmpty()){
            throw new MyException("Field cannot be empty");
        }

        for (Administrator otherAdministrator : administratorArrayList){
            if (otherAdministrator.getUsername().equals(administrator.getUsername())){
                throw new MyException("Admin username already exists");
            }
        }

    }

    public static void validateSalesman(Salesman salesman, ArrayList<Salesman> salesmanArrayList) throws MyException {

        if (salesman.getUsername().isEmpty() || salesman.getPassword().isEmpty()){
            throw new MyException("Field cannot be empty");
        }

        for (Salesman otherSalesman : salesmanArrayList){
            if (otherSalesman.getUsername().equals(salesman.getUsername())){
                throw new MyException("Salesman username already exists");
            }
        }

    }

    public static void validateClient(Client client, ArrayList<Client> clients) throws MyException {

        if (client.getCnp().isEmpty() || client.getName().isEmpty() || client.getAddress().isEmpty()){
            throw new MyException("Field cannot be empty");
        }

        if (!client.getName().matches("[a-zA-Z]+")){
            throw new MyException("Invalid name");
        }

        if (!client.getCnp().matches("[0-9]+") || client.getCnp().length() != 13){
            throw new MyException("Invalid CNP");
        }
    }

}

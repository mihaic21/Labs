package Controller;

import Model.Administrator;
import Model.Salesman;
import Repository.RepoInterface;
import Utils.MyException;

/**
 * Created by mihai on 22.05.2014.
 */
public class LoginController {

    public static void validateLogin(String loginType, String username, String password, RepoInterface repository) throws MyException {

        if (loginType.equals("Administrator")){
            boolean found = false;
            for (Object administrator : repository.getAllAdministrators()){
                Administrator admin = (Administrator) administrator;
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                    found = true;
                }
            }
            if (!found){
                throw new MyException("Invalid username or password");
            }

        }else if (loginType.equals("Salesman")){
            boolean found = false;
            for (Object salesman : repository.getAllSalesmen()){
                Salesman sale = (Salesman) salesman;
                if (sale.getUsername().equals(username) && sale.getPassword().equals(password)){
                    found = true;
                }
            }
            if (!found){
                throw new MyException("Invalid username or password");
            }
        }

    }

}

import Controller.Controller;
import Model.Product;
import Repository.RepoInterface;
import Repository.RepositoryDB;
import UI.HomeForm;

/**
 * Created by mihai on 19.05.2014.
 */
public class Main {

    public static void main(String[] args){

        RepoInterface<Product> repoInterface = new RepositoryDB("Products");
//        Service service = new Service(repoInterface);
        Controller controller = new Controller(repoInterface);
        HomeForm homeForm = new HomeForm(controller);

        //Controller controller1 = new Controller(repoInterface);
        //HomeForm homeForm1 = new HomeForm(controller1);

    }

}

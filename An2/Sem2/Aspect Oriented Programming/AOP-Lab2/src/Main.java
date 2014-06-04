import Controller.Controller;
import Model.Product;
import Repository.RepoInterface;
import Repository.RepositoryDB;
import Service.Service;
import UI.HomeForm;

public class Main {

    public static void main(String[] args) {

        RepoInterface<Product> repoInterface = new RepositoryDB("Products");
        Service service = new Service(repoInterface);
        Controller controller = new Controller(service);
        HomeForm homeForm = new HomeForm(controller);
    }
}

import Repository.RepoInterface;
import Repository.RepositoryDB;
import View.HomeForm;

/**
 * Created by mihai on 22.05.2014.
 */
public class Main {

    public static void main(String[] args){

        String productsTableName = "Products";
        String administratorsTableName = "Administrators";
        String salesmenTableName = "Salesmen";
        String clientsTableName = "Clients";
        String ordersTableName = "Orders";

        RepoInterface repository = new RepositoryDB(productsTableName, administratorsTableName, salesmenTableName,
                clientsTableName, ordersTableName);

        HomeForm homeForm = new HomeForm(repository);

    }

}

import Controller.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by mihai on 26.05.2014.
 */
public class Main {

    public static void main(String[] args){

        //Repository<Product> repository = new RepositoryDB("Products"); //not needed
        //Repository<Product> repository = new RepositoryDAO();
        //Controller controller = new Controller(repository);
        //HomeForm homeForm = new HomeForm(controller);

        Properties properties = new Properties(System.getProperties());
        try {
            properties.load(new FileReader("src/selling-agent.properties"));
            System.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApplicationContext context = new ClassPathXmlApplicationContext("selling-agent-dao.xml");

        ApplicationContext context1 = new ClassPathXmlApplicationContext("security.xml");

        final ControllerInterface controller = (ControllerInterface) context.getBean("controller");

    }

}

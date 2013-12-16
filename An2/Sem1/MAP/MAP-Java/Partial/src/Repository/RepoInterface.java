package Repository;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/16/13
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RepoInterface<T> {

    public ArrayList<T> getElements();
    public void writeToFile(ArrayList<T> elementsToWrite);
    public void replaceContent(ArrayList<T> content);

}

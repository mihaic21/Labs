package Repository;

import java.util.ArrayList;

/**
 * Created by mihai on 2/3/14.
 */
public interface RepoInterface<T> {

    public ArrayList<T> getElements();
    public void writeToFile(ArrayList<T> elementsToWrite);
    public void replaceContent(ArrayList<T> content);
    public void addElement(T element);

}

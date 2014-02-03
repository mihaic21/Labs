package Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mihai on 2/3/14.
 */
public class Repository<T> implements RepoInterface<T> {

    private ArrayList<T> elements = new ArrayList<T>();

    @Override
    public ArrayList<T> getElements() {
        return elements;
    }

    @Override
    public void writeToFile(ArrayList<T> elementsToWrite) {
        try{
            FileWriter out = new FileWriter("output.txt");
            for (Object elem : elementsToWrite){
                out.write(elem.toString());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replaceContent(ArrayList<T> content) {
        elements = content;
    }

    @Override
    public void addElement(T element) {
        this.elements.add(element);
    }


}

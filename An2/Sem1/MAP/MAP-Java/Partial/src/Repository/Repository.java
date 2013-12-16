package Repository;

import Model.Pom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 12/16/13
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Repository implements RepoInterface {

    private ArrayList elements;

    @Override
    public ArrayList getElements() {
        return elements;
    }

    @Override
    public void writeToFile(ArrayList elementsToWrite) {
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
    public void replaceContent(ArrayList content) {
        elements = content;
    }

}

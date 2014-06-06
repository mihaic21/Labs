package Graph;

import Utils.MyException;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * Created by mihai on 01.06.2014.
 */
public interface UGraphInterface {

    void addEdge(int x, int y) throws MyException;
    void removeEdge(int x, int y) throws MyException;

    int getVertexCount();
    int getEdgeCount();
    boolean getIfEdge(int x, int y) throws MyException;
    boolean getIfVertex(int x);
    int getDegree(int x) throws MyException;
    ArrayList<Integer> getNeighbors(int x) throws MyException;
    ArrayList<Integer> getVertices();

    void addVertex();
    void removeVertex(int i) throws MyException;

    Map<Integer, Map<Integer, Integer>> getAllEdges();

    void readGraphFromFile(String fileName) throws MyException;

    void printGraph();

    boolean isPath(int x, int y) throws MyException;


}

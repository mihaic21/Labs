package Graph;

import Utils.MyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by mihai on 01.06.2014.
 */
public class UGraph implements UGraphInterface {

    private Map<Integer, Map<Integer, Integer>> edges;
    protected ArrayList<Integer> vertices;
    protected int vertexCount;
    protected int edgeCount;

    public UGraph(){

        edges = new HashMap<Integer, Map<Integer, Integer>>();
        vertices = new ArrayList<Integer>();
        vertexCount = 0;
        edgeCount = 0;

    }

    public UGraph(UGraphInterface graphInterface){

        edges = new HashMap<Integer, Map<Integer, Integer>> (graphInterface.getAllEdges());
        vertices = new ArrayList<Integer>(graphInterface.getVertices());
        vertexCount = graphInterface.getVertexCount();
        edgeCount = graphInterface.getEdgeCount();

    }

    @Override
    public void addEdge(int x, int y) throws MyException {

        if (getIfEdge(x, y) == true) {
            throw new MyException("Such edge exists!");
        }

        edges.get(x).put(y, 0);
        edges.get(y).put(x, 0);

        edgeCount++;
    }

    @Override
    public void removeEdge(int x, int y) throws MyException {

        if (!getIfEdge(x, y)){
            throw new MyException("No such edge!");
        }

        edges.get(x).remove(y);
        edges.get(y).remove(x);

        edgeCount--;

    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public boolean getIfEdge(int x, int y) throws MyException {
        if (!getIfVertex(x) || !getIfVertex(y)){
            throw new MyException("Invalid vertex");
        }
        if (edges.size() == 0){
            return false;
        }
        if (edges.get(x).size() == 0){
            return false;
        }
        return edges.get(x).containsKey(y) || edges.get(y).containsKey(x);
    }

    @Override
    public boolean getIfVertex(int x) {
        return vertices.contains(x);
    }

    @Override
    public int getDegree(int x) throws MyException {
        if (!getIfVertex(x)){
            throw new MyException("Invalid Vertex");
        }
        return edges.get(x).size();
    }

    @Override
    public ArrayList<Integer> getNeighbors(int x) throws MyException {
        if (!getIfVertex(x)){
            throw new MyException("Invalid Vertex");
        }
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int key : edges.get(x).keySet()){
            list.add(key);
        }
        return list;
    }

    @Override
    public ArrayList<Integer> getVertices() {
        return vertices;
    }

    @Override
    public void addVertex() {
        vertices.add(vertices.size());
        vertexCount++;
//        if (!edges.containsKey(vertices.size() - 1)){
//            edges.put(vertices.size() -1, new HashMap<Integer, Integer>());
//        }
        edges.put(vertices.size() - 1, new HashMap<Integer, Integer>());
    }

    @Override
    public void removeVertex(int x) throws MyException {
        if (!getIfVertex(x)){
            throw new MyException("Invalid vertex");
        }
        edges.get(x).clear();
        Map<Integer, Map<Integer, Integer>> newMap = new HashMap<Integer, Map<Integer, Integer>>(edges);
        for (Map.Entry<Integer, Map<Integer, Integer>> edge : edges.entrySet()){
            if (edge.getValue().containsKey(x)){
                edge.getValue().remove(x);
                edgeCount--;
            }
        }
        vertices.remove(x);
        vertexCount--;
    }

    @Override
    public Map<Integer, Map<Integer, Integer>> getAllEdges() {
        return edges;
    }

    @Override
    public void readGraphFromFile(String fileName) throws MyException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            String[] tokens = line.split(" ");
            int vertexCountAux = Integer.parseInt(tokens[0]);
            int edgeCountAux = Integer.parseInt(tokens[1]);
            for (int i = 0; i < vertexCountAux; i++){
                addVertex();
            }
            while ((line = bufferedReader.readLine()) != null){
                tokens = line.split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                addEdge(x, y);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new MyException("File not found");
        } catch (IOException e) {
            throw new MyException("Problem while reading");
        } catch (NumberFormatException e){
            throw new MyException("Invalid types");
        }

    }

    @Override
    public void printGraph() {
        for (int key1 : edges.keySet()){
            for (int key2 : edges.get(key1).keySet())
            System.out.println("From " + key1 + " to " + key2);
        }
    }

    @Override
    public boolean isPath(int x, int y) throws MyException {

        Queue<Integer> queue = new PriorityQueue<Integer>();
        ArrayList<Integer> visited = new ArrayList<Integer>();

        queue.add(x);
        visited.add(x);

        while (queue.size() != 0){

            int node = queue.remove();
            if (node == y){
                return true;
            } else {
                for (int neighbour : getNeighbors(node)){
                    if (!queue.contains(neighbour) && !visited.contains(neighbour)){
                        visited.add(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }
        return false;
    }
}
